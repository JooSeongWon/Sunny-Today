package xyz.sunnytoday.common.repository;

public class ForecastTemperature {
    private final String baseDate;

    private int temperature;


    public ForecastTemperature(String baseDate, int temperature) {
        this.baseDate = baseDate;
        this.temperature = temperature;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "ForecastTemperature{" +
                "baseDate='" + baseDate + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
