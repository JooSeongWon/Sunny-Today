package xyz.sunnytoday.common.task;

import xyz.sunnytoday.service.face.WeatherService;

import java.util.Calendar;
import java.util.Date;

public class WeatherRefreshTask extends TaskTimer {
    private boolean isFirst;
    private boolean isSetTime;

    private final WeatherService weatherService = null;

    public WeatherRefreshTask(int interval) {
        super(interval);
        isFirst = true;
    }

    @Override
    protected void start() {
        if (!isSetTime) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
            int nowMinute = calendar.get(Calendar.MINUTE);

            // 중기예보 갱신 시각 첫호출 OR (06시 10분 이후 / 18시 10분이후 첫 호출)
            if (isFirst) {
                isFirst = false;
            } else if (nowMinute <= 10 || !(nowHour == 6 || nowHour == 18)) {
                return;
            } else {
                isSetTime = true;
                super.setInterval(12 * 60); // 12시간 마다 반복
            }
        }


        // 중기예보 갱신 (06 / 18시)

    }
}