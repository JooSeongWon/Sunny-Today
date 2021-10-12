package xyz.sunnytoday.service.impl;

import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.Region;
import xyz.sunnytoday.service.face.ForecastService;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForecastServiceImpl implements ForecastService {

    public void requestGeoLocationData(Region region) throws IOException,HTTPException {
        String authenticationKey = AppConfig.getAppKeyRepository().getKey("shortTermForecast", "encoding");

        //api url
        String requestUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
        requestUrl +=
                "?serviceKey=" +
                        authenticationKey +
                        "&numOfRows=1000" + //한 페이지 결과수
                        "&pageNo=1" +
                        "&dataType=JSON" +
                        "&base_date=" + //발표 일
                        AppConfig.getForecastRepository().getLastShortTermForecastVersion().substring(0,8) +
                        "&base_time=" + //발표 시간
                        AppConfig.getForecastRepository().getLastShortTermForecastVersion().substring(8) +
                        "&nx=" + //타일 x좌표
                        region.getX() +
                        "&ny=" + //타일 y좌표
                        region.getY();

        URL url = new URL(requestUrl);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode != 200) {
            throw new HTTPException(responseCode);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        bufferedReader.close();

//        JsonObject rootNode = JsonParser.parseString(stringBuffer.toString()).getAsJsonObject();
//        JsonObject geoLocation = rootNode.getAsJsonObject("geoLocation");
//
//        Map<String, String> result = new HashMap<>();
//        result.put("country", geoLocation.get("country").getAsString());
//        result.put("r1", geoLocation.get("r1").getAsString());
//        result.put("r2", geoLocation.get("r2").getAsString());

        System.out.println(stringBuilder);
    }
}
