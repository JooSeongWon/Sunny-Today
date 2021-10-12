package xyz.sunnytoday.common.repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ForecastRepository {
    private final String[] shortTermUpdateTimes = {"0200", "0500", "0800", "1100", "1400", "1700", "2000", "2300"};
    private final Map<String, Region> forecastMap;
    private String lastShortTermForecastVersion;

    public ForecastRepository(Map<String, Region> foreCastMap) {
        this.forecastMap = foreCastMap;
        this.lastShortTermForecastVersion = "";
    }

    public String getLastShortTermForecastVersion() {
        return lastShortTermForecastVersion;
    }

    public boolean updateLastShortTermForecastVersion() {
        boolean isUpdated = false;

        Date now = new Date();
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

    public boolean isContainsCity(String city) {
        return this.forecastMap.containsKey(city);
    }

    public List<Forecast> getShortTermForecast(String city) throws IOException {
        return this.forecastMap.get(city).getShortTermForecasts();
    }

    public boolean needShortTermForecastUpdate(String city) {
        return this.forecastMap.get(city).needShortTermForecastUpdate();
    }

    public void updateShortTermForeCast(String city, List<Forecast> shortTermForecasts) {
        this.forecastMap.get(city).updateShortTermForeCast(shortTermForecasts, this.lastShortTermForecastVersion);
    }

    public int[] getBlockPosition(String city) {
        Region region = this.forecastMap.get(city);
        int[] position = new int[2];

        position[0] = region.getX();
        position[1] = region.getY();

        return position;
    }
}
