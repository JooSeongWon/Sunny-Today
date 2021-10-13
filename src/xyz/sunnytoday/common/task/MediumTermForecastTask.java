package xyz.sunnytoday.common.task;

import xyz.sunnytoday.service.face.ForecastService;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;

public class MediumTermForecastTask extends TaskTimer {
    private boolean isFirst = true;

    public MediumTermForecastTask(int interval) {
        super(interval);
    }

    @Override
    protected void start() {
        ForecastService forecastService = new ForecastServiceImpl();
        try {
            if (forecastService.updateMediumTermForecast()) {
                System.out.println("[INFO] 중기예보가 업데이트 되었습니다.");
                if (isFirst) {
                    isFirst = false;
                } else {
                    super.setInterval(12 * 60);
                }
            }
        } catch (Exception e) {
            System.out.println("[ERROR] 중기 날씨조회에 실패했습니다. 빈 날씨 객체로 대체합니다." + e.getMessage());

            //서비스 이용해서 빈객체 채우기 작업!!
            forecastService.setEmptyMediumTermForecast();


            //다시 10분간격으로 데이터 다시 받아오기 시도
            isFirst = true;
            super.setInterval(10);
        }
    }
}
