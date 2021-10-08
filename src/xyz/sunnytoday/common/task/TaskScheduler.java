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
        System.out.println("[INFO] InterruptedException, 서버 종료시에 exception이 발생 했다면 스레드를 안전하게 깨운후 종료하기위해 호출 된 것으로 정상입니다.");
    }
}
