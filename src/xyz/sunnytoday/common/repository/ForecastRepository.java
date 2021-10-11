package xyz.sunnytoday.common.repository;

import java.util.Map;

public class ForecastRepository {
    private final Map<String, Region> foreCastMap;
    private String lastShortTermForecastVersion;

    public ForecastRepository(Map<String, Region> foreCastMap) {
        this.foreCastMap = foreCastMap;
    }

    public String getLastShortTermForecastVersion() {
        return lastShortTermForecastVersion;
    }

    public void setLastShortTermForecastVersion(String lastShortTermForecastVersion) {
        this.lastShortTermForecastVersion = lastShortTermForecastVersion;
    }
}
