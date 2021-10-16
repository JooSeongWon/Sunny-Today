package xyz.sunnytoday.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.service.face.GeoLocationService;

class GeoLocationServiceImplTest {

    @Test
    @DisplayName("위치정보 탐색 테스트")
    void GeoLocationResponseTest() throws Exception {
        AppConfig.Init("test/"); //키값 넣고 테스트 할것

        GeoLocationService geoLocationService = new GeoLocationServiceImpl();
        String[] response;
        try {
            //서울 39.115.110.153
            //부천 58.77.135.71
            response = geoLocationService.requestGeoLocationData("39.115.110.153");

            for (int i = 0; i < response.length; i++) {
                System.out.println("response[" + i + "] = " + response[i]);
            }

            response = geoLocationService.requestGeoLocationData("58.77.135.71");

            for (int i = 0; i < response.length; i++) {
                System.out.println("response[" + i + "] = " + response[i]);
            }

            response = geoLocationService.requestGeoLocationData("106.102.128.15");

            for (int i = 0; i < response.length; i++) {
                System.out.println("response[" + i + "] = " + response[i]);
            }
        } catch (Exception e) {
            throw e;
        }

        AppConfig.destroy();
    }

}