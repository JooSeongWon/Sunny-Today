package xyz.sunnytoday.common.task;

public class CustomTaskTimer extends TaskTimer {
    private final Runnable runnable;

    public CustomTaskTimer(int interval, Runnable runnable) {
        super(interval);
        this.runnable = runnable;
    }

    @Override
    protected void start() {
        runnable.run();
    }
}
