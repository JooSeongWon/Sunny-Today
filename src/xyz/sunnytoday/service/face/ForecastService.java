package xyz.sunnytoday.service.face;

import xyz.sunnytoday.common.repository.Forecast;

import java.io.IOException;
import java.util.List;

/**
 * 예보정보 조회 서비스입니다.
 */
public interface ForecastService {

    /**
     * 해당 도시의 단기 날씨정보 (금일 현재시각 ~ 6시간 + 내일 + 모레 15시 날씨정보)
     * @param city 검색할 도시이름 ex) 서울특별시, 경기도부천시 등
     * @return 날씨정보 리스트
     * @throws IOException API사용이 필요할경우 API를 통해 정보를 읽으면서 오류가 발생한 경우 throw합니다.
     */
    List<Forecast> getShortTermForecast(String city) throws IOException;
}
