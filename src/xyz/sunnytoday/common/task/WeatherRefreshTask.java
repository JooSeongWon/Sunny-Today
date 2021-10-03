package xyz.sunnytoday.common.task;

import xyz.sunnytoday.service.face.WeatherService;

import java.util.Calendar;
import java.util.Date;

public class WeatherRefreshTask extends TaskTimer {
    private boolean isFirst;

    private final WeatherService weatherService = null;

    public WeatherRefreshTask(int interval) {
        super(interval);
    }

    @Override
    protected void start() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        // 중기예보 갱신 시각 첫호출 OR (06시 / 18시 대 호출)
        if (calendar.get(Calendar.HOUR_OF_DAY) != 6
                && calendar.get(Calendar.HOUR_OF_DAY) != 18
                && !isFirst) {

            return;
        }

        // 중기예보 갱신 (06 / 18시)
        isFirst = true;
        


    }
}