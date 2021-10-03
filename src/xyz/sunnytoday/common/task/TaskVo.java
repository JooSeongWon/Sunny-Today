package xyz.sunnytoday.common.task;

public class TaskVo {
    private final String name;
    private final String classUrl;
    private final int interval;

    public TaskVo(String name, String classUrl, int interval) {
        this.name = name;
        this.classUrl = classUrl;
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public String getClassUrl() {
        return classUrl;
    }

    public int getInterval() {
        return interval;
    }
}
