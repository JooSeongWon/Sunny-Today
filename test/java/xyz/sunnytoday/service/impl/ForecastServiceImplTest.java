package xyz.sunnytoday.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.service.face.ForecastService;

import java.io.IOException;

class ForecastServiceImplTest {

    @Test
    @DisplayName("단기 예보 api 테스트")
    void shortTermForecastTest() {
        AppConfig.Init("test/"); //키값 넣고 테스트 할것

        try {
            ForecastService forecastService = new ForecastServiceImpl();
            forecastService.getShortTermForecast("경기도부천시").forEach(System.out::println);
            forecastService.getShortTermForecast("경기도부천시").forEach(System.out::println);
            forecastService.getShortTermForecast("서울특별시").forEach(System.out::println);
            forecastService.getShortTermForecast("경기도부천시").forEach(System.out::println);
            forecastService.getShortTermForecast("서울특별시").forEach(System.out::println);
        }catch (IOException e ) {
            e.printStackTrace();
        }

        AppConfig.destroy();
    }
}