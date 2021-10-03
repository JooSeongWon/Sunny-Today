package xyz.sunnytoday.appconfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.AppKeyRepository;

import java.util.Calendar;
import java.util.Date;

public class AppConfigInitTest {

    @Test
    @DisplayName("앱키 가져오기")
    void AppKeyInitTest() {
        AppConfig.Init("test/app-config.xml");
        AppKeyRepository appKeyRepository = AppConfig.getAppKeyRepository();

        Assertions.assertEquals(appKeyRepository.getKey("mediumTermForecast", "encoding")
                , "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad, asperiores assumenda, at aut cupiditate deserunt dolorem dolorum fugiat harum itaque molestiae nulla obcaecati odio officia officiis porro quibusdam tempora voluptatum?");
        Assertions.assertEquals(appKeyRepository.getKey("mediumTermForecast", "decoding")
                , "Lorem ipsum dolor sit amet, consectetur.");

        Assertions.assertEquals(appKeyRepository.getKey("test", "en")
                , "arum itaque molestiae nulla obcaecati odio officia officiis porro quibusdam tempora voluptatum?");
        Assertions.assertEquals(appKeyRepository.getKey("test", "de")
                , "nulla obcaecati odio officia officiis porro quibusdam tempora voluptatum%%");

        AppConfig.destroy();
    }

    @Test
    void dateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("calendar.get(Calendar.HOUR) = " + calendar.get(Calendar.HOUR_OF_DAY));
    }
}
