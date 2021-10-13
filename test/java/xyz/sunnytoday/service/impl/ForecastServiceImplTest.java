package xyz.sunnytoday.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.service.face.ForecastService;

class ForecastServiceImplTest {

    @Test
    @DisplayName("단기, 중기 예보 api 테스트")
    void shortTermForecastTest() {
        AppConfig.Init("test/"); //키값 넣고 테스트 할것

        ForecastService forecastService = new ForecastServiceImpl();
        //단기
        forecastService.getShortTermForecast("경기도부천시").forEach(System.out::println);
        //중기
        forecastService.getMediumTermForecast("경기도부천시").forEach(System.out::println);

        AppConfig.destroy();
    }
}