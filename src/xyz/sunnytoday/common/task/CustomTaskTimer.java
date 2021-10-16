package xyz.sunnytoday.common.task;

public class CustomTaskTimer extends TaskTimer {
    private final Runnable runnable;

    public CustomTaskTimer(int interval, Runnable runnable, boolean isFirstTimeStart) {
        super(interval);
        this.runnable = runnable;
        //첫번째 실행 무시합니다.
        if(!isFirstTimeStart) super.setNoFirstTimeStart();
    }

    @Override
    protected void start() {
        runnable.run();
    }
}
