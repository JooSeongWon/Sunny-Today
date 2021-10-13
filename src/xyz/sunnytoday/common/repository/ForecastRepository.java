package xyz.sunnytoday.common.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ForecastRepository {
    private final String[] shortTermUpdateTimes = {"0200", "0500", "0800", "1100", "1400", "1700", "2000", "2300"};
    private final Map<String, Region> forecastMap;
    private Map<String, List<ForecastWeather>> mediumTermWeathers;
    private Map<String, List<ForecastTemperature>> mediumTermTemperatures;
    private String lastShortTermForecastVersion;
    private String lastMediumTermForecastVersion;

    public ForecastRepository(Map<String, Region> foreCastMap) {
        this.forecastMap = foreCastMap;
        this.lastShortTermForecastVersion = "";
        this.lastMediumTermForecastVersion = "";
    }

    public String getLastShortTermForecastVersion() {
        return lastShortTermForecastVersion;
    }

    public String getLastMediumTermForecastVersion() {
        return lastMediumTermForecastVersion;
    }

    public void setLastMediumTermForecastVersion(String lastMediumTermForecastVersion) {
        this.lastMediumTermForecastVersion = lastMediumTermForecastVersion;
    }

    public boolean updateLastShortTermForecastVersion() {
        boolean isUpdated = false;

        Date now = new Date();
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(now));
        if (hour < 2) {
            String tmp = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "2300";
            if (!tmp.equals(this.lastShortTermForecastVersion)) {
                this.lastShortTermForecastVersion = tmp;
                isUpdated = true;
            }
            return isUpdated;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        String date = dateFormat.format(now);
        String time = timeFormat.format(now);
        int numOfTime = Integer.parseInt(time);

        for (int i = this.shortTermUpdateTimes.length - 1; i >= 0; i--) {
            if (Integer.parseInt(this.shortTermUpdateTimes[i]) + 10 < numOfTime) { //갱신시간 10분뒤부터 api로 날씨를 제공해줌
                String ver = date + this.shortTermUpdateTimes[i];
                if (!this.lastShortTermForecastVersion.equals(ver)) {
                    this.lastShortTermForecastVersion = ver;
                    isUpdated = true;
                }
                break;
            }
        }

        return isUpdated;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isContainsCity(String city) {
        return this.forecastMap.containsKey(city);
    }

    public Region getRegion(String city) {
        return this.forecastMap.get(city);
    }

    public void setMediumTermWeathers(Map<String, List<ForecastWeather>> mediumTermWeathers) {
        this.mediumTermWeathers = mediumTermWeathers;
    }

    public List<String> getMediumTermWeatherKeyList() {
        return new ArrayList<>(this.mediumTermWeathers.keySet());
    }

    public void setMediumTermTemperatures(Map<String, List<ForecastTemperature>> mediumTermTemperatures) {
        this.mediumTermTemperatures = mediumTermTemperatures;
    }

    public List<String> getMediumTermTemperatureKeyList() {
        return new ArrayList<>(this.mediumTermTemperatures.keySet());
    }

    public List<Forecast> getMediumTermForecast(String city) {
        List<Forecast> list = new ArrayList<>();
        Region region = this.forecastMap.get(city);

        List<ForecastWeather> forecastWeathers = this.mediumTermWeathers.get(region.getForecastCode());
        List<ForecastTemperature> forecastTemperatures = this.mediumTermTemperatures.get(region.getTemperatureCode());

        for (int i = 0; i < forecastWeathers.size(); i++) {
            for (int j = i; j < forecastTemperatures.size(); j++) {
                if (forecastWeathers.get(i).getBaseDate().equals(forecastTemperatures.get(j).getBaseDate())) {
                    Forecast forecast = new Forecast(forecastWeathers.get(i), forecastTemperatures.get(j));
                    list.add(forecast);
                }
            }
        }

        return list;
    }
}
