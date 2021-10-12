package xyz.sunnytoday.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;

import java.io.IOException;

class ForecastServiceImplTest {

    @Test
    @DisplayName("단기 예보 api 테스트")
    void shortTermForecastTest() {
        AppConfig.Init("test/"); //키값 넣고 테스트 할것

        try {
            AppConfig.getForecastRepository().setLastShortTermForecastVersion("202110121100");
            AppConfig.getForecastRepository().getShortTermForecast("경기도부천시");
        }catch (IOException e ) {
            e.printStackTrace();
        }

        AppConfig.destroy();
    }
}