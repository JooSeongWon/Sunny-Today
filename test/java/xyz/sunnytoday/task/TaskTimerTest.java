package xyz.sunnytoday.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.common.task.TaskTimer;

public class TaskTimerTest {

    @Test
    @DisplayName("타이머 원하는 틱마다 작동확인")
    void timerRunATick() {
        TestTimer timer1 = new TestTimer(5);
        TestTimer timer2 = new TestTimer(0);
        TestTimer timer3 = new TestTimer(14);
        TestTimer timer4 = new TestTimer(30);
        TestTimer timer5 = new TestTimer(55);

        for(int i = 0; i < 6; i++) {  //6번(30분) 반복 가정
            timer1.run();
            timer2.run();
            timer3.run();
            timer4.run();
            timer5.run();
        }

        Assertions.assertEquals(timer1.tick, 6);
        Assertions.assertEquals(timer2.tick, 6);
        Assertions.assertEquals(timer3.tick, 4);
        Assertions.assertEquals(timer4.tick, 2);
        Assertions.assertEquals(timer5.tick, 1);

    }

    static class TestTimer extends TaskTimer {
        int tick = 0;

        public TestTimer(int interval) {
            super(interval);
        }

        @Override
        protected void start() {
            tick++;
        }
    }
}
