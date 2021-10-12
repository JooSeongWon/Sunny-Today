package xyz.sunnytoday.common.task;

import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.ForecastRepository;

public class ShortTermForecastTask extends TaskTimer{
    private final ForecastRepository forecastRepository = AppConfig.getForecastRepository();

    public ShortTermForecastTask(int interval) {
        super(interval);
    }

    @Override
    protected void start() {
        if(this.forecastRepository.updateLastShortTermForecastVersion()) {
           super.setInterval(3 * 60);
        }
    }
}
