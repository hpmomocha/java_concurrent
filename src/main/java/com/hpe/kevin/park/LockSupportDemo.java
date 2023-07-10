package com.hpe.kevin.park;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "c.LockSupportDemo")
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        interruptParkedThread();
    }

    private static void interruptParkedThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("中断标记: {}", Thread.currentThread().isInterrupted());

            // 中断标记为真时, 再次park会失效, [park failed...]会打印出来
            LockSupport.park();
            log.debug("park failed...");

            // 调用 Thread.interrupted() 后, 会消除中断标记(中断标记为false), 再次park会生效, [park success...]不会打印出来
            log.debug("中断标记: {}", Thread.interrupted());
            LockSupport.park();
            log.debug("park success...");

        }, "t1");

        t1.start();

        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }
}
