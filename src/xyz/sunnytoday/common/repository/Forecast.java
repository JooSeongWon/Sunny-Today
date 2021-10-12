package xyz.sunnytoday.common.repository;

public class Forecast {
    public static final int SUNNY = 1;
    public static final int CLOUDY = 3;
    public static final int BLUR = 4;

    private final int temperatures;
    private final int chanceOfRain;
    private final String weather;
    private final String baseDate;
    private final String baseTime;

    public Forecast(String baseDate, String baseTime, int temperatures, int chanceOfRain, String weather) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.temperatures = temperatures;
        this.chanceOfRain = chanceOfRain;
        this.weather = weather;
    }


    public int getTemperatures() {
        return temperatures;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public String getWeather() {
        return weather;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "temperatures=" + temperatures +
                ", chanceOfRain=" + chanceOfRain +
                ", weather='" + weather + '\'' +
                ", baseDate='" + baseDate + '\'' +
                ", baseTime='" + baseTime + '\'' +
                '}';
    }
}
