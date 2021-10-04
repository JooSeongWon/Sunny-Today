package xyz.sunnytoday.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.AppKeyRepository;
import xyz.sunnytoday.dto.GeoLocationDto;
import xyz.sunnytoday.service.face.GeoLocationService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GeoLocationServiceImpl implements GeoLocationService {
    private final AppKeyRepository appKeyRepository = AppConfig.getAppKeyRepository();

    public GeoLocationDto requestGeoLocationData(String ipAddress) throws IOException, NoSuchAlgorithmException, InvalidKeyException, HTTPException {
        //api url
        String hostName = "https://geolocation.apigw.ntruss.com";
        String requestUrl = "/geolocation/v2/geoLocation";
        String timestamp = Long.toString(System.currentTimeMillis());
        requestUrl = requestUrl +
                "?ext=t&" + // 추가정보 받기
                "&ip=" +
                ipAddress +
                "&responseFormatType=json";
        String urlAndParams = hostName + requestUrl;
        URL url = new URL(urlAndParams);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
        httpURLConnection.setRequestProperty("x-ncp-iam-access-key", appKeyRepository.getKey("geoLocation", "access"));
        httpURLConnection.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(timestamp, requestUrl));


        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode != 200) {
            throw new HTTPException(responseCode);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuffer = new StringBuilder();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        JsonObject rootNode = JsonParser.parseString(stringBuffer.toString()).getAsJsonObject();
        JsonObject geoLocation = rootNode.getAsJsonObject("geoLocation");
        return new GeoLocationDto(geoLocation.get("country").getAsString()
                , geoLocation.get("code").getAsString()
                , geoLocation.get("r1").getAsString()
                , geoLocation.get("r2").getAsString()
                , geoLocation.get("r3").getAsString()
                , geoLocation.get("lat").getAsFloat()
                , geoLocation.get("long").getAsFloat());
    }

    // 시그니처
    private String makeSignature(String timestamp, String url) throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";// one space
        String newLine = "\n";// new line
        String method = "GET";
        String accessKey = appKeyRepository.getKey("geoLocation", "access");
        String secretKey = appKeyRepository.getKey("geoLocation", "secret");

        String message = method +
                space +
                url +
                newLine +
                timestamp +
                newLine +
                accessKey;

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        System.out.println(url);
        System.out.println(Base64.encodeBase64String(rawHmac));
        return Base64.encodeBase64String(rawHmac);
    }
}
