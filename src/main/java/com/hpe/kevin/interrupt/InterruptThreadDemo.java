package com.hpe.kevin.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.InterruptSleepingThreadDemo")
public class InterruptThreadDemo {
    public static void main(String[] args) throws InterruptedException {
//        interruptSleepingThread();
        interruptRunningThread();
    }

    /**
     * 线程在 运行的情况下(wait, sleep, join 以外)被中断, 中断标记不会被清除
     * 但是调用 Thread 类的静态方法 interrupted() 也会清除中断标记
     * @throws InterruptedException
     */
    private static void interruptRunningThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                boolean interrupted = currentThread.isInterrupted();
                // 如果没有这个判断, 则 while 循环会继续
                // 因为 interrupt() 方法只是将线程的中断标记标识为 true
                if (interrupted) {
                    log.debug("被打断了, 退出循环");
                    break;
                }
            }
        });

        t1.start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("interrupt");
        t1.interrupt();
    }

    /**
     * 线程在 wait, sleep, join 的情况下被中断, 中断标记会被清除
     * @throws InterruptedException
     */
    private static void interruptSleepingThread() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("sleep...");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("interrupt t1");
        t1.interrupt();
        // 此时再休眠100ms的意图是防止获取 t1 的中断标记有延迟
        Thread.sleep(100);
        log.debug("打断标记:{}", t1.isInterrupted());
    }
}
