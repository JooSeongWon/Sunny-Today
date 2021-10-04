package xyz.sunnytoday.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.dto.GeoLocationDto;
import xyz.sunnytoday.service.face.GeoLocationService;

class GeoLocationServiceImplTest {

    @Test
    @DisplayName("위치정보 탐색 테스트")
    void GeoLocationResponseTest() throws Exception {
        AppConfig.Init("test/app-config2.xml");

        GeoLocationService geoLocationService = new GeoLocationServiceImpl();
        GeoLocationDto response = null;
        try {
            response = geoLocationService.requestGeoLocationData("58.77.135.71");
        } catch (Exception e) {
            throw e;
        }

        System.out.println(response);
        AppConfig.destroy();
    }

}