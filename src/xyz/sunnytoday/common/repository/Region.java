package xyz.sunnytoday.common.repository;

import xyz.sunnytoday.common.config.AppConfig;
import java.util.List;

public class Region {
    private final String country;
    private final String r1;
    private final String r2;
    private final String code;
    private final int x;
    private final int y;

    private String shortTermForecastVersion;
    private List<Forecast> shortTermForecasts;
    private List<Forecast> mediumTermForecasts;

    public Region(String country, String r1, String r2, String code, int x, int y) {
        this.country = country;
        this.r1 = r1;
        this.r2 = r2;
        this.code = code;
        this.x = x;
        this.y = y;
    }

    public String getCode() {
        return code;
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public String getCountry() {
        return country;
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

    public List<Forecast> getMediumTermForecasts() {
        return mediumTermForecasts;
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

    //중기예보 업데이트
    public void updateMediumTermForeCast(List<Forecast> mediumTermForecasts) {
        this.mediumTermForecasts = mediumTermForecasts;
    }

    @Override
    public String toString() {
        return "Region{" +
                "country='" + country + '\'' +
                ", r1='" + r1 + '\'' +
                ", r2='" + r2 + '\'' +
                ", code='" + code + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", shortTermForecastVersion='" + shortTermForecastVersion + '\'' +
                ", shortTermForecasts=" + shortTermForecasts +
                ", mediumTermForecasts=" + mediumTermForecasts +
                '}';
    }
}
