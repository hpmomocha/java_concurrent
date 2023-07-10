package com.hpe.kevin.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

public class TwoPhaseTerminationDemo {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();

        Thread.sleep(3500);

        twoPhaseTermination.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(()->{
            while (true) {
                if (monitor.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 在 sleep 中的线程被中断, 会将中断标记清除
                    // 所以需要重新中断标记
                    monitor.interrupt();
                }
            }
        });

        monitor.start();

    }

    public void stop() {
        monitor.interrupt();
    }
}
