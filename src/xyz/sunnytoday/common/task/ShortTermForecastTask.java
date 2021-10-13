package xyz.sunnytoday.common.task;


import xyz.sunnytoday.common.config.AppConfig;

public class ShortTermForecastTask extends TaskTimer {

    public ShortTermForecastTask(int interval) {
        super(interval);
    }

    @Override
    protected void start() {
        if (AppConfig.getForecastRepository().updateLastShortTermForecastVersion()) {
            System.out.println("[INFO] 단기예보가 업데이트 되었습니다.");
            super.setInterval(3 * 60);
        }
    }
}
