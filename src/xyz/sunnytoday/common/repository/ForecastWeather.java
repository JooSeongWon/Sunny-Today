package xyz.sunnytoday.common.repository;

public class ForecastWeather {
    public static final int SUNNY = 1;
    public static final int CLOUDY = 3;
    public static final int BLUR = 4;
    private final String baseDate;

    private String weather;
    private int chanceOfRain;

    public ForecastWeather(String baseDate, String weather, int chanceOfRain) {
        this.baseDate = baseDate;
        this.weather = weather;
        this.chanceOfRain = chanceOfRain;
    }

    public String getBaseDate() {
        return baseDate;
    }


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(int chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    @Override
    public String toString() {
        return "ForecastWeather{" +
                "baseDate='" + baseDate + '\'' +
                ", weather='" + weather + '\'' +
                ", chanceOfRain=" + chanceOfRain +
                '}';
    }
}
