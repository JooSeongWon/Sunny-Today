package xyz.sunnytoday.common.task;

public abstract class TaskTimer implements Runnable {
    private boolean isFirst;
    private final int interval;
    private int tick;

    public TaskTimer(int interval) {
        // 스케쥴러를 5분에 한번씩 실행할 것임으로 5분단위로 체크해야함
        interval = interval / 5;
        this.interval = interval <= 0 ? 1 : interval;
        this.isFirst = true;
    }

    @Override
    public final void run() { // 오버라이딩 금지
        if (++tick == interval) {
            tick = 0;
            this.start();
        } else if (isFirst) {
            isFirst = false;
            this.start();
        }
    }

    protected abstract void start();
}
