package xyz.sunnytoday.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.*;
import xyz.sunnytoday.service.face.ForecastService;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings("StringBufferReplaceableByString")
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository = AppConfig.getForecastRepository();

    @Override
    public List<Forecast> getShortTermForecast(String city) {

        if (!this.forecastRepository.isContainsCity(city)) {
            throw new IllegalArgumentException("없는 도시입니다.");
        }

        Region region = this.forecastRepository.getRegion(city);

        if (region.needShortTermForecastUpdate()) {
            try {
                this.updateShortTermForecast(region);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return region.getShortTermForecasts();
    }

    @Override
    public List<Forecast> getMediumTermForecast(String city) {
        if (!this.forecastRepository.isContainsCity(city)) {
            throw new IllegalArgumentException("없는 도시입니다.");
        }

        return this.forecastRepository.getMediumTermForecast(city);
    }


    @Override
    public boolean updateMediumTermForecast() throws IOException, HTTPException {
        String authenticationKey = AppConfig.getAppKeyRepository().getKey("mediumTermForecast", "encoding");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HHmm");

        String baseDate;
        String baseTime;

        Date now = new Date();
        int nowHourMin = Integer.parseInt(simpleTimeFormat.format(now));
        if (nowHourMin < 630) {
            baseDate = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            baseTime = "1800";
        } else {
            baseDate = simpleDateFormat.format(now);
            if (nowHourMin < 1830) {
                baseTime = "0600";
            } else {
                baseTime = "1800";
            }
        }

        //업데이트 필요여부 판단
        if (this.forecastRepository.getLastMediumTermForecastVersion().equals(baseDate + baseTime)) {
            return false;
        }

        //api url
        String fixedUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey=" +
                authenticationKey +
                "&dataType=JSON";

        List<String> weatherKeys = this.forecastRepository.getMediumTermWeatherKeyList();
        List<String> temperatureKeys = this.forecastRepository.getMediumTermTemperatureKeyList();

        Map<String, List<ForecastWeather>> forecastWeatherMap = new HashMap<>();


        //중기예보 기상정보 저장
        for (String weatherKey : weatherKeys) {

            List<ForecastWeather> list = new ArrayList<>();

            StringBuilder builder = new StringBuilder();
            builder.append(fixedUrl)
                    .append("&regId=")
                    .append(weatherKey)
                    .append("&tmFc=")
                    .append(baseDate)
                    .append(baseTime);

            URL url = new URL(builder.toString());
            String responseJson = getResponseJson(url);

            //item 아래 아이템이 하나만 들어옴
            JsonObject rootNode = JsonParser.parseString(responseJson).getAsJsonObject().getAsJsonObject("response");
            JsonObject item = rootNode.getAsJsonObject("body").getAsJsonObject("items").getAsJsonArray("item").get(0).getAsJsonObject();

            //3~10일뒤 날씨 저장
            for (int i = 3; i <= 10; i++) {
                list.add(makeForecastWeather(baseDate, item, i));
            }

            //맵에 저장
            forecastWeatherMap.put(weatherKey, list);
        }

        this.forecastRepository.setMediumTermWeathers(forecastWeatherMap);


        // 중기예보 온도 저장
        fixedUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey=" +
                authenticationKey +
                "&dataType=JSON";

        Map<String, List<ForecastTemperature>> forecastTemperatureMap = new HashMap<>();

        for (String temperatureKey : temperatureKeys) {

            List<ForecastTemperature> list = new ArrayList<>();

            StringBuilder builder = new StringBuilder();
            builder.append(fixedUrl)
                    .append("&regId=")
                    .append(temperatureKey)
                    .append("&tmFc=")
                    .append(baseDate)
                    .append(baseTime);

            URL url = new URL(builder.toString());
            String responseJson = getResponseJson(url);

            //item 아래 아이템이 하나만 들어옴 (기상정보와 같음)
            JsonObject rootNode = JsonParser.parseString(responseJson).getAsJsonObject().getAsJsonObject("response");
            JsonObject item = rootNode.getAsJsonObject("body").getAsJsonObject("items").getAsJsonArray("item").get(0).getAsJsonObject();

            for (int i = 3; i <= 10; i++) {
                list.add(maskForecastTemperature(baseDate, item, i));
            }

            forecastTemperatureMap.put(temperatureKey, list);
        }

        this.forecastRepository.setMediumTermTemperatures(forecastTemperatureMap);


        this.forecastRepository.setLastMediumTermForecastVersion(baseDate + baseTime);
        return true;
    }


    private ForecastTemperature maskForecastTemperature(String baseDate, JsonObject item, int afterDays) {
        String format = "yyyyMMdd";
        LocalDateTime baseLocalDate = LocalDateTime.parse(baseDate + "000000", DateTimeFormatter.ofPattern(format + "HHmmss"));

        int min = item.get("taMin" + afterDays).getAsInt();
        min = ((min - item.get("taMin" + afterDays + "Low").getAsInt()) +
                (min + item.get("taMin" + afterDays + "High").getAsInt())) / 2;

        int max = item.get("taMax" + afterDays).getAsInt();
        max = ((max - item.get("taMax" + afterDays + "Low").getAsInt()) +
                (max + item.get("taMax" + afterDays + "High").getAsInt())) / 2;

        return new ForecastTemperature(baseLocalDate.plusDays(afterDays).format(DateTimeFormatter.ofPattern(format)), (min + max) / 2);
    }

    private ForecastWeather makeForecastWeather(String baseDate, JsonObject item, int afterDays) {
        String format = "yyyyMMdd";
        LocalDateTime baseLocalDate = LocalDateTime.parse(baseDate + "000000", DateTimeFormatter.ofPattern(format + "HHmmss"));
        ForecastWeather forecastWeather;

        if (afterDays < 8) {
            forecastWeather = new ForecastWeather(
                    baseLocalDate.plusDays(afterDays).format(DateTimeFormatter.ofPattern(format)), //날짜 포맷
                    getWorstWeather(item.get("wf" + afterDays + "Am").getAsString(), item.get("wf" + afterDays + "Pm").getAsString()), //오전, 오후중 안좋은 날씨
                    Math.max(item.get("rnSt" + afterDays + "Am").getAsInt(), item.get("rnSt" + afterDays + "Pm").getAsInt())); //오전, 오후중 최고 강수량
        } else {
            forecastWeather = new ForecastWeather(
                    baseLocalDate.plusDays(afterDays).format(DateTimeFormatter.ofPattern(format)), //날짜 포맷
                    getWorstWeather("맑음", item.get("wf" + afterDays).getAsString()), //날씨 하나밖에 없음
                    item.get("rnSt" + afterDays).getAsInt()); //강수량도 하나밖에 없음
        }

        return forecastWeather;
    }

    private String getWorstWeather(String amWeather, String pmWeather) {
        switch (Math.max(parseWeather(amWeather), parseWeather(pmWeather))) {
            case ForecastWeather.SUNNY:
                return "맑음";
            case ForecastWeather.CLOUDY:
                return "구름많음";
            case ForecastWeather.BLUR:
                return "흐림";
        }

        System.out.println("[Error] 중기예보 파싱 중 없는 날씨 : 발생해서는 안돼는 예외! ForecastServiceImpl 확인필요");
        return "맑음";
    }

    private int parseWeather(String weather) {
        switch (weather) {
            case "맑음":
                return ForecastWeather.SUNNY;
            case "흐림":
                return ForecastWeather.BLUR;
            case "구름많음":
                return ForecastWeather.CLOUDY;
            default:
                String subStr = weather.substring(0, 2);
                if (subStr.equals("흐리")) {
                    return ForecastWeather.BLUR;
                } else if (subStr.equals("구름")) {
                    return ForecastWeather.CLOUDY;
                }

                System.out.println("[Error] 중기예보 파싱 중 없는 날씨 : 맑음으로 대체 - " + weather);
        }

        return ForecastWeather.SUNNY;
    }

    private void updateShortTermForecast(Region region) throws IOException, HTTPException {
        String authenticationKey = AppConfig.getAppKeyRepository().getKey("shortTermForecast", "encoding");
        String baseDate = AppConfig.getForecastRepository().getLastShortTermForecastVersion().substring(0, 8);
        String baseTime = AppConfig.getForecastRepository().getLastShortTermForecastVersion().substring(8);

        //api url
        StringBuilder builder = new StringBuilder();

        builder.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                .append("?serviceKey=")
                .append(authenticationKey)
                .append("&numOfRows=1000") //한 페이지 결과수
                .append("&pageNo=1")
                .append("&dataType=JSON")
                .append("&base_date=") //발표 일
                .append(baseDate)
                .append("&base_time=") //발표 시간
                .append(baseTime)
                .append("&nx=") //타일 x좌표
                .append(region.getX())
                .append("&ny=") //타일 y좌표
                .append(region.getY());

        URL url = new URL(builder.toString());

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        String responseJson = getResponseJson(url);

        //결과
        List<Forecast> shortTermForecasts = new ArrayList<>();

        JsonObject rootNode = JsonParser.parseString(responseJson).getAsJsonObject().getAsJsonObject("response");
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
                if (shortTermForecasts.size() == 0 &&
                        Integer.parseInt(itemObj.get("fcstTime").getAsString()) < nowTime &&
                        itemObj.get("fcstDate").getAsString().equals(baseDate)) {
                    continue;
                }

                //지금부터 총 7시간치 날씨 그리고 내일이랑 모레 (가끔은 글피까지) 날씨는 15시 기준 저장
                if (shortTermForecasts.size() < 7 ||
                        (!itemObj.get("fcstDate").getAsString().equals(baseDate) && itemObj.get("fcstTime").getAsString().equals("1500"))) {

                    temp = Integer.parseInt(itemObj.get("fcstValue").getAsString());
                    willSave = true;
                }

            } else if (willSave && itemObj.get("category").getAsString().equals("SKY")) { //하늘상태
                int weather = Integer.parseInt(itemObj.get("fcstValue").getAsString());

                switch (weather) {
                    case ForecastWeather.SUNNY:
                        sky = "맑음";
                        break;
                    case ForecastWeather.CLOUDY:
                        sky = "구름많음";
                        break;
                    case ForecastWeather.BLUR:
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

        region.updateShortTermForeCast(shortTermForecasts, this.forecastRepository.getLastShortTermForecastVersion());
    }

    private String getResponseJson(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            System.out.println(httpURLConnection.getResponseMessage());
            throw new HTTPException(responseCode);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        bufferedReader.close();

        return stringBuilder.toString();
    }
}