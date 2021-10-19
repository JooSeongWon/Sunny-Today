package xyz.sunnytoday.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.service.face.SocialLoginService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class SocialLoginServiceImpl implements SocialLoginService {
    //소셜 맴버 타입 상수
    public static final int TYPE_NAVER = 0;
    public static final int TYPE_GOOGLE = 1;

    @Override
    public String createNaverLoginUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        String clientId = AppConfig.getAppKeyRepository().getKey("naverLogin", "clientId");//애플리케이션 클라이언트 아이디값";
        String redirectURI = URLEncoder.encode(AppConfig.getAppKeyRepository().getKey("domain", "host") + "/login/naver", "UTF-8");

        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();

        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;

        request.getSession().setAttribute("state", state);

        return apiURL;
    }

    @Override
    public String createGoogleLoginUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        String clientId = AppConfig.getAppKeyRepository().getKey("googleLogin", "clientId");
        String redirectURI = URLEncoder.encode(AppConfig.getAppKeyRepository().getKey("domain", "host") + "/login/google", "UTF-8");

        String apiURL = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code&scope=https%3A//www.googleapis.com/auth/userinfo.email";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;

        return apiURL;
    }

    @Override
    public String getEmailOrNull(HttpServletRequest request, int socialType) {

        try {
            if (socialType == TYPE_NAVER) {
                String accessToken = getNaverAccessTokenOrNull(request);
                if (accessToken != null) {
                    return getEmailByAccessToken(accessToken, TYPE_NAVER);
                }
                return null;
            } else if (socialType == TYPE_GOOGLE) {
                String accessToken = getGoogleAccessTokenOrNull(request);
                if (accessToken != null) {
                    return getEmailByAccessToken(accessToken, TYPE_GOOGLE);
                }
                return null;
            }
        } catch (Exception e) {
            System.out.println("[ERROR] 소셜 로그인 콜백을 읽을 수 없습니다.");
            e.printStackTrace();
            return null;
        }
        return null;
    }


    //네이버 로그인으로부터 엑세스토큰을 얻는다.
    private String getNaverAccessTokenOrNull(HttpServletRequest request) throws UnsupportedEncodingException {
        String clientId = AppConfig.getAppKeyRepository().getKey("naverLogin", "clientId");//애플리케이션 클라이언트 아이디값";
        String clientSecret = AppConfig.getAppKeyRepository().getKey("naverLogin", "clientSecret");//애플리케이션 클라이언트 시크릿값";
        String redirectURI = URLEncoder.encode(AppConfig.getAppKeyRepository().getKey("domain", "host") + "/login/naver", "UTF-8");

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        request.getSession().setAttribute("state", null);

        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return parseAccessToken(con);
        } catch (Exception e) {
            System.out.println("[ERROR] 소셜로그인 서비스 : " + e);
        }

        return null;
    }

    //구글 로그인으로부터 엑세스토큰을 얻는다.
    private String getGoogleAccessTokenOrNull(HttpServletRequest request) throws UnsupportedEncodingException {
        String clientId = AppConfig.getAppKeyRepository().getKey("googleLogin", "clientId");//애플리케이션 클라이언트 아이디값";
        String clientSecret = AppConfig.getAppKeyRepository().getKey("googleLogin", "clientSecret");//애플리케이션 클라이언트 시크릿값";
        String redirectURI = URLEncoder.encode(AppConfig.getAppKeyRepository().getKey("domain", "host") + "/login/google", "UTF-8");

        String code = request.getParameter("code");


        String apiURL = "https://oauth2.googleapis.com/token";

        String params = "grant_type=authorization_code";
        params += "&client_id=" + clientId;
        params += "&client_secret=" + clientSecret;
        params += "&redirect_uri=" + redirectURI;
        params += "&code=" + code;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(params);
            writer.flush();
            writer.close();
            os.close();


            return parseAccessToken(con);
        } catch (Exception e) {
            System.out.println("[ERROR] 소셜로그인 서비스 : " + e);
        }

        return null;
    }

    //액세스 토큰을 이용해 이메일을 얻는다. (네이버 or 구글)
    private String getEmailByAccessToken(String accessToken, int socialType) {
        String header = "Bearer " + accessToken;

        String apiURL;
        if (socialType == TYPE_NAVER) {
            apiURL = "https://openapi.naver.com/v1/nid/me";
        } else {
            apiURL = "https://www.googleapis.com/oauth2/v2/userinfo";
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = getResponseBody(apiURL, requestHeaders);

        //바디 읽어서 메일값만 추출
        JsonObject rootNode;

        if (socialType == TYPE_NAVER) {
            rootNode = JsonParser.parseString(responseBody).getAsJsonObject().getAsJsonObject("response");
        } else {
            rootNode = JsonParser.parseString(responseBody).getAsJsonObject();
        }
        return rootNode.get("email").getAsString();

    }


    //응답으로부터 액세스토큰 파싱
    private String parseAccessToken(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        BufferedReader br;

        if (responseCode == 200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        StringBuilder res = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }

        br.close();
        if (responseCode == 200) {
            //결과 파싱 - 엑세스토근 얻기
            Gson gson = new Gson();
            Map<String, String> result = new HashMap<>();

            //noinspection unchecked
            result = gson.fromJson(String.valueOf(res), result.getClass());

            return result.get("access_token");
        }

        System.out.println("[ERROR] 소셜로그인 서비스 : " + res);
        return null;
    }

    private static String getResponseBody(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }


}
