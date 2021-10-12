package xyz.sunnytoday.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.repository.Region;
import xyz.sunnytoday.service.face.GeoLocationService;

import java.util.Map;

class GeoLocationServiceImplTest {

    @Test
    @DisplayName("위치정보 탐색 테스트")
    void GeoLocationResponseTest() throws Exception {
        AppConfig.Init("test/"); //키값 넣고 테스트 할것

        GeoLocationService geoLocationService = new GeoLocationServiceImpl();
        Map<String, String > response;
        try {
            response = geoLocationService.requestGeoLocationData("58.77.135.71");
        } catch (Exception e) {
            throw e;
        }

        System.out.println(response);
        AppConfig.destroy();
    }

}