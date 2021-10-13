package xyz.sunnytoday.common.task;

import xyz.sunnytoday.service.impl.ForecastServiceImpl;

import java.io.IOException;

public class MediumTermForecastTask extends TaskTimer {
    public MediumTermForecastTask(int interval) {
        super(interval);
    }

    @Override
    protected void start() {
        try {
            if (new ForecastServiceImpl().updateMediumTermForecast()) {
                System.out.println("[INFO] 중기예보가 업데이트 되었습니다.");
                super.setInterval(12 * 60);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
