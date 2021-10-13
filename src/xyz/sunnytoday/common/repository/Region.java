package xyz.sunnytoday.common.repository;

import xyz.sunnytoday.common.config.AppConfig;

import java.util.List;

public class Region {
    private final String r1;
    private final String r2;
    private final int x;
    private final int y;
    private final String forecastCode;
    private final String temperatureCode;

    private String shortTermForecastVersion;
    private List<Forecast> shortTermForecasts;

    public Region(String r1, String r2, int x, int y, String forecastCode, String temperatureCode) {
        this.r1 = r1;
        this.r2 = r2;
        this.x = x;
        this.y = y;
        this.forecastCode = forecastCode;
        this.temperatureCode = temperatureCode;
    }

    public String getForecastCode() {
        return forecastCode;
    }

    public String getTemperatureCode() {
        return temperatureCode;
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Forecast> getShortTermForecasts() {
        return shortTermForecasts;
    }


    //업데이트 해야하는지 여부
    public boolean needShortTermForecastUpdate() {
        return this.shortTermForecastVersion == null
                || !this.shortTermForecastVersion.equals(AppConfig.getForecastRepository().getLastShortTermForecastVersion());
    }

    //단기예보 업데이트
    public void updateShortTermForeCast(List<Forecast> shortTermForecasts, String shortTermForecastVersion) {
        this.shortTermForecastVersion = shortTermForecastVersion;
        this.shortTermForecasts = shortTermForecasts;
    }
}
