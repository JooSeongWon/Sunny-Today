package xyz.sunnytoday.common.repository;

public class ForeCast {
    private final int minTemperatures;
    private final int maxTemperatures;
    private final int chanceOfRain;
    private final String weather;
    private final String baseDate;
    private final String baseTime;

    public ForeCast(String baseDate, String baseTime, int minTemperatures, int maxTemperatures, int chanceOfRain, String weather) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.minTemperatures = minTemperatures;
        this.maxTemperatures = maxTemperatures;
        this.chanceOfRain = chanceOfRain;
        this.weather = weather;
    }

    public int getMinTemperatures() {
        return minTemperatures;
    }

    public int getMaxTemperatures() {
        return maxTemperatures;
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
}
