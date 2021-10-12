package xyz.sunnytoday.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.Forecast;
import xyz.sunnytoday.common.repository.ForecastRepository;
import xyz.sunnytoday.service.face.ForecastService;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository = AppConfig.getForecastRepository();

    private void updateShortTermForecast(String city) throws IOException, HTTPException {
        System.out.println("ForecastServiceImpl.updateShortTermForecast");
        int[] position = forecastRepository.getBlockPosition(city);
        String authenticationKey = AppConfig.getAppKeyRepository().getKey("shortTermForecast", "encoding");
        String baseDate = AppConfig.getForecastRepository().getLastShortTermForecastVersion().substring(0, 8);
        String baseTime = AppConfig.getForecastRepository().getLastShortTermForecastVersion().substring(8);

        //api url
        String requestUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
        requestUrl +=
                "?serviceKey=" +
                        authenticationKey +
                        "&numOfRows=1000" + //한 페이지 결과수
                        "&pageNo=1" +
                        "&dataType=JSON" +
                        "&base_date=" + //발표 일
                        baseDate +
                        "&base_time=" + //발표 시간
                        baseTime +
                        "&nx=" + //타일 x좌표
                        position[0] +
                        "&ny=" + //타일 y좌표
                        position[1];

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


        //결과
        List<Forecast> shortTermForecasts = new ArrayList<>();

        JsonObject rootNode = JsonParser.parseString(stringBuilder.toString()).getAsJsonObject().getAsJsonObject("response");
        JsonArray items = rootNode.getAsJsonObject("body").getAsJsonObject("items").getAsJsonArray("item");


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        int nowTime = Integer.parseInt(simpleDateFormat.format(new Date()));
        int temp = 0;
        String sky = null;
        boolean willSave = false;

        for (JsonElement item : items) {
            JsonObject itemObj = item.getAsJsonObject();
            if (itemObj.get("category").getAsString().equals("TMP")) { //기온

                //처음에는 지금시간보다 작으면 거름
                if (shortTermForecasts.size() == 0 && Integer.parseInt(itemObj.get("fcstTime").getAsString()) < nowTime) {
                    continue;
                }

                //지금부터 총 7시간치 날씨 그리고 내일이랑 모레 (가끔은 글피까지) 날씨는 15시 기준 저장
                if (
                        shortTermForecasts.size() < 7 ||
                                (!itemObj.get("fcstDate").getAsString().equals(baseDate) && itemObj.get("fcstTime").getAsString().equals("1500"))
                ) {

                    temp = Integer.parseInt(itemObj.get("fcstValue").getAsString());
                    willSave = true;
                }

            } else if (willSave && itemObj.get("category").getAsString().equals("SKY")) { //하늘상태
                int weather = Integer.parseInt(itemObj.get("fcstValue").getAsString());

                switch (weather) {
                    case Forecast.SUNNY:
                        sky = "맑음";
                        break;
                    case Forecast.CLOUDY:
                        sky = "구름많음";
                        break;
                    case Forecast.BLUR:
                        sky = "흐림";
                        break;
                }

            } else if (willSave && itemObj.get("category").getAsString().equals("POP")) { //강수 확률
                Forecast forecast = new Forecast(itemObj.get("fcstDate").getAsString(), //날짜
                        itemObj.get("fcstTime").getAsString(), //시간
                        temp, //기온
                        Integer.parseInt(itemObj.get("fcstValue").getAsString()), //강수확률
                        sky); //구름상태

                shortTermForecasts.add(forecast);
                willSave = false;
            }
        }

        forecastRepository.updateShortTermForeCast(city, shortTermForecasts);
    }

    @Override
    public List<Forecast> getShortTermForecast(String city) throws IOException {

        if (!forecastRepository.isContainsCity(city)) {
            throw new IllegalArgumentException("없는 도시입니다.");
        }
        if (forecastRepository.needShortTermForecastUpdate(city)) {
            this.updateShortTermForecast(city);
        }

        return forecastRepository.getShortTermForecast(city);
    }
}