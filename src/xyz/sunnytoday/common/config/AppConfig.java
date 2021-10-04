package xyz.sunnytoday.common.config;

import xyz.sunnytoday.common.repository.AppKeyRepository;
import xyz.sunnytoday.common.task.TaskScheduler;
import xyz.sunnytoday.common.task.TaskConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AppConfig {
    private static AppConfig instance;
    private final AppKeyRepository appKeyRepository;
    private final TaskScheduler taskScheduler;

    // 객체생성, 의존성 주입
    private AppConfig(String url) {
        //API 키
        XmlConfigParser xmlConfigParser = new XmlConfigParser(url);
        appKeyRepository = new AppKeyRepository(xmlConfigParser.getAppKeys());

        //반복 작업
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

        //실행
        taskScheduler.enable();
    }


    // 초기화 구문
    public static void Init(String xmlConfigFileUrl) {
        // 첫 생성 객체가 항상 그대로 싱글턴으로 유지됨을 보장한다.
        if (instance != null) {
            return;
        }

        instance = new AppConfig(xmlConfigFileUrl);
    }

    public static AppKeyRepository getAppKeyRepository() {
        return instance.appKeyRepository;
    }

    // 자원정리
    public static void destroy() {
        instance.taskScheduler.disable();
    }
}
