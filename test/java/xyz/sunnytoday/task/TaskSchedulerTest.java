package xyz.sunnytoday.task;

import org.junit.jupiter.api.*;
import xyz.sunnytoday.common.task.TaskScheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskSchedulerTest {

    /* 테스트하려면 5분 필요함 */
    @Test
    @DisplayName("스케쥴러 정상 작동 확인")
    void schedulerRunTest() {
        TaskScheduler taskScheduler = new TaskScheduler();

        AtomicInteger min = new AtomicInteger(-1);
        taskScheduler.addTask(() -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            if (min.get() == -1) {
                min.set(calendar.get(Calendar.MINUTE));
            } else {
                int secondMin = calendar.get(Calendar.MINUTE);
                if (min.get() + 5 > 60) {
                    secondMin += 60;
                }

                // 5분 간격 실행확인
                Assertions.assertEquals(secondMin, min.get() + 5);
            }
        });
        taskScheduler.enable();
        try {
            Thread.sleep(5 * 60 * 1000 + (10 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 쓰레드를 깨우기위해 인터럽트 입셉션 로그 발생은 정상
    }
}
