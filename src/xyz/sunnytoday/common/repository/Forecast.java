package xyz.sunnytoday.common.repository;

public class Forecast {
    private final String baseDate;
    private final String baseTime;

    private final ForecastWeather forecastWeather;
    private final ForecastTemperature forecastTemperature;


    public Forecast(String baseDate, String baseTime, int temperature, int chanceOfRain, String weather) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.forecastWeather = new ForecastWeather(baseDate, weather, chanceOfRain);
        this.forecastTemperature = new ForecastTemperature(baseDate, temperature);
    }

    //중기예보 생성
    public Forecast(ForecastWeather forecastWeather, ForecastTemperature forecastTemperature) {
        this.baseDate = forecastWeather.getBaseDate();
        this.baseTime = "0000";
        this.forecastWeather = forecastWeather;
        this.forecastTemperature = forecastTemperature;
    }

    public int getTemperature() {
        return this.forecastTemperature.getTemperature();
    }

    public int getChanceOfRain() {
        return this.forecastWeather.getChanceOfRain();
    }

    public String getWeather() {
        return this.forecastWeather.getWeather();
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
                "baseDate='" + baseDate + '\'' +
                ", baseTime='" + baseTime + '\'' +
                ", forecastWeather=" + forecastWeather +
                ", forecastTemperature=" + forecastTemperature +
                '}';
    }
}
