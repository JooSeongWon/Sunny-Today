package xyz.sunnytoday.common.task;

public class Task {
    private final String name;
    private final String classUrl;
    private final int interval;

    public Task(String name, String classUrl, int interval) {
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
