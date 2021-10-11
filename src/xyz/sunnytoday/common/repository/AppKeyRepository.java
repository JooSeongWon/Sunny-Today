package xyz.sunnytoday.common.repository;

import java.util.Map;

public class AppKeyRepository {
    private final Map<String, Appkey> appKeys;

    public AppKeyRepository(Map<String, Appkey> appKeys) {
        this.appKeys = appKeys;
    }

    // 리포지토리에서 임뮤터블한 API키(String) 만을 꺼낼 수 있음으로, 처음 로딩된 API키값이 변경될 수 없음을 보장한다.
    public String getKey(String apiName, String keyName) {
        return appKeys.get(apiName).getKey(keyName);
    }
}