package xyz.sunnytoday.common.config;

import xyz.sunnytoday.common.repository.AppKeyRepository;

public class AppConfig {
    private static AppConfig instance;
    private final AppKeyRepository appKeyRepository;



    // 객체생성, 의존성 주입
    private AppConfig(String url){
        XmlConfigParser xmlConfigParser = new XmlConfigParser(url);
        appKeyRepository = new AppKeyRepository(xmlConfigParser.getAppKeys());
    }


    // 초기화 구문
    public static void Init(String xmlConfigFileUrl) {
        // 첫 생성 객체가 항상 그대로 싱글턴으로 유지됨을 보장한다.
        if(instance != null) {
            return;
        }

        instance = new AppConfig(xmlConfigFileUrl);
    }

    public static AppKeyRepository getAppKeyRepository() {
        return instance.appKeyRepository;
    }

    // 자원정리
    public static void destroy() {
    }
}
