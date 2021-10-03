package xyz.sunnytoday.common.task;

import java.util.ArrayList;
import java.util.List;

public class TaskScheduler implements Runnable {
    private final List<Runnable> taskList;
    private final Thread thread;
    private boolean isEnable;

    public TaskScheduler() {
        taskList = new ArrayList<>();
        thread = new Thread(this);
    }

    public void addTask(Runnable task) {
        taskList.add(task);
    }

    @Override
    public void run() {
        while (isEnable) {
            taskList.forEach(Runnable::run);
            try {
                //noinspection BusyWait
                Thread.sleep(5 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enable() {
        if(isEnable) {
            return;
        }

        isEnable = true;
        thread.start();
    }

    public void disable() {
        if(!isEnable) {
            return;
        }

        isEnable = false;
        thread.interrupt();
    }
}
