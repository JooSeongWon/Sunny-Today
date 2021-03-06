package xyz.sunnytoday.common.config;

import xyz.sunnytoday.common.repository.AppKeyRepository;
import xyz.sunnytoday.common.repository.Appkey;
import xyz.sunnytoday.common.repository.ForecastRepository;
import xyz.sunnytoday.common.repository.TemporaryMemberRepository;
import xyz.sunnytoday.common.task.TaskScheduler;
import xyz.sunnytoday.common.task.TaskConfig;
import xyz.sunnytoday.common.task.TaskTimer;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AppConfig {
    private static AppConfig instance;
    private final AppKeyRepository appKeyRepository;
    private final TaskScheduler taskScheduler;
    private final TemporaryMemberRepository temporaryMemberRepository;
    private ForecastRepository forecastRepository;

    // 초기화 구문
    public static void Init(String configPath) {
        // 첫 생성 객체가 항상 그대로 싱글턴으로 유지됨을 보장한다.
        if (instance != null) {
            return;
        }

        instance = new AppConfig(configPath);

        //task 실행
        instance.taskScheduler.enable();
    }

    public static AppKeyRepository getAppKeyRepository() {
        return instance.appKeyRepository;
    }

    public static ForecastRepository getForecastRepository() {
        return instance.forecastRepository;
    }

    public static void addTask(TaskTimer taskTimer) {
        instance.taskScheduler.addTask(taskTimer);
    }

    public static TemporaryMemberRepository getTemporaryMemberRepo(){
        return instance.temporaryMemberRepository;
    }

    // 자원정리
    public static void destroy() {
        instance.taskScheduler.disable();
    }

    // 객체생성, 의존성 주입
    private AppConfig(String path) {
        //API 키
        XmlConfigParser xmlConfigParser = new XmlConfigParser(path + "app-config.xml");
        appKeyRepository = new AppKeyRepository(xmlConfigParser.getAppKeys());

        //예보 저장소
        RegionParser regionParser = new RegionParser(path + "city.properties");
        try {
            forecastRepository = new ForecastRepository(regionParser.parseRegions());
        } catch (IOException e) {
            e.printStackTrace();
        }
        forecastRepository.updateLastShortTermForecastVersion();
        forecastRepository.setMediumTermWeathers(regionParser.getForecastWeatherMap());
        forecastRepository.setMediumTermTemperatures(regionParser.getForecastTemperatureMap());

        //Task
        taskScheduler = new TaskScheduler();
        List<TaskConfig> taskList = xmlConfigParser.getTasks();
        taskList.forEach(taskConfig -> {
            try {
                Constructor<?> constructor = Class.forName(taskConfig.getClassUrl()).getConstructor(Integer.TYPE);
                taskScheduler.addTask((Runnable) constructor.newInstance(taskConfig.getInterval()));
            } catch (ClassNotFoundException
                    | NoSuchMethodException
                    | InstantiationException
                    | IllegalAccessException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        //회원 임시 저장소
        temporaryMemberRepository = new TemporaryMemberRepository();
    }
}
