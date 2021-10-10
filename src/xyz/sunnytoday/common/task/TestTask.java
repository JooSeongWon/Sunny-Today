package xyz.sunnytoday.common.task;

public class TestTask extends TaskTimer{
    public TestTask(int interval) {
        super(interval);
    }

    @Override
    protected void start() {
        System.out.println("TestTask.start!");
        System.out.println("this = " + this);
    }
}
