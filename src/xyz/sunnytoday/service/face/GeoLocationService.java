package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 네이버 클라우드 플랫폼을 활용하여 ip 주소를 통해 위도, 경도를 받아온다.
 * 무료로 사용할 수 있는 html5 스펙으로 지원하는 geolocation은 상당 수 브라우저에서 https 사용시에만 허용하고 있다.
 * 네이버 클라우드 플랫폼 geolocation은 건당 이용요금이 발생할 수 있으며 모바일 네트워크에서 지원하지 않아서 주의가 필요하다.
 */
public interface GeoLocationService {
    /**
     * 네이버 클라우드 플랫폼의 geolocation 요청 수행로직
     *
     * @param ipAddress 위치를 알아낼 대상의 ip주소
     * @return 결과값 GeoLocation DTO
     */
    Map<String, String> requestGeoLocationData(String ipAddress) throws IOException, NoSuchAlgorithmException, InvalidKeyException;

    /**
     * 요청을 한 클라이언트의 ip주소를 얻어온다.
     * @param request 요청
     * @return ip주소
     */
    String getIpAddress(HttpServletRequest request);
}
