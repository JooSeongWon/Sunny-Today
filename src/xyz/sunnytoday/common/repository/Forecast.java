package xyz.sunnytoday.common.repository;

public class Forecast {
    private final String baseDate;
    private final String baseTime;

    private final ForecastWeather forecastWeather;
    private final ForecastTemperature forecastTemperature;

    //빈객체 (api 오류가 너무 많아요 ㅠㅠ 공공api... 정상데이터 수신 못한경우 빈객체 반환용)
    private static final Forecast emptyInstance = new Forecast("0000", "0000", 0, 0, "맑음");

    public static Forecast getEmptyInstance() {
        return emptyInstance;
    }

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

    public int getIntBastTime() {
        return Integer.parseInt(baseTime) / 100;
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
