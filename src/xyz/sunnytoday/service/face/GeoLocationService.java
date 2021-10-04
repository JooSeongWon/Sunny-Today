package xyz.sunnytoday.service.face;

import xyz.sunnytoday.dto.GeoLocationDto;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 네이버 클라우드 플랫폼을 활용하여 ip 주소를 통해 위도, 경도를 받아온다.
 * 무료로 사용할 수 있는 html5 스펙으로 지원하는 geolocation은 상당 수 브라우저에서 https 사용시에만 허용하고 있다.
 * 네이버 클라우드 플랫폼 geolocation은 건당 이용요금이 발생할 수 있으며 모바일 네트워크에서 지원하지 않아서 주의가 필요하다.
 * <br><br>
 * <b>본 인터페이스는 네이버 클라우드 플랫폼을 활용한 위치정보 서비스 로직이다.</b>
 */
public interface GeoLocationService {
    /**
     * 네이버 클라우드 플랫폼의 geolocation 요청 수행로직
     *
     * @param ipAddress 위치를 알아낼 대상의 ip주소
     * @return 결과값 (json text)
     */
    GeoLocationDto requestGeoLocationData(String ipAddress) throws IOException, NoSuchAlgorithmException, InvalidKeyException;
}
