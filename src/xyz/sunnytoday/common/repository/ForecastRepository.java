package xyz.sunnytoday.common.repository;

import xyz.sunnytoday.service.face.ForecastService;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ForecastRepository {
    private final Map<String, Region> forecastMap;
    private final ForecastService forecastService = new ForecastServiceImpl();
    private String lastShortTermForecastVersion;

    public ForecastRepository(Map<String, Region> foreCastMap) {
        this.forecastMap = foreCastMap;
    }

    public String getLastShortTermForecastVersion() {
        return lastShortTermForecastVersion;
    }

    public void setLastShortTermForecastVersion(String lastShortTermForecastVersion) {
        this.lastShortTermForecastVersion = lastShortTermForecastVersion;
    }

    public List<Forecast> getShortTermForecast(String city) throws IOException {
        if (!this.forecastMap.containsKey(city)) {
            throw new IllegalArgumentException("도시이름이 정확하지 않습니다.");
        }

        Region region = this.forecastMap.get(city);
        if(region.needShortTermForecastUpdate()) {
            //업데이트 로직
            forecastService.requestGeoLocationData(region);
        }

        return region.getShortTermForecasts();
    }
}
