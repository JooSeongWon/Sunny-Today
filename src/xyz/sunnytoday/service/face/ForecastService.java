package xyz.sunnytoday.service.face;

import xyz.sunnytoday.common.repository.Forecast;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;

/**
 * 예보정보 조회 서비스입니다.
 */
public interface ForecastService {

    /**
     * 해당 도시의 단기 날씨정보 (금일 현재시각 ~ 6시간 + 내일 + 모레 15시 날씨정보)
     *
     * @param city 검색할 도시이름 ex) 서울특별시, 경기도부천시 등
     * @return 날씨정보 리스트
     */
    List<Forecast> getShortTermForecast(String city);

    /**
     * 모든도시의 중기 날씨정보 캐시를 업데이트 합니다. (매일 2번)
     * @return 업데이트 됐으면 true반환
     */
    boolean updateMediumTermForecast() throws IOException, HTTPException;

    /**
     * 해당도시의 중기 날씨정보 (3일후 ~ 10일후)를 검색합니다.
     *
     * @param city 도시이름
     * @return 날씨정보 리스트
     */
    List<Forecast> getMediumTermForecast(String city);
}
