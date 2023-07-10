package com.hpe.kevin.threadstate;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.ThreadStateDemo")
public class ThreadStateDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{});

        Thread t2 = new Thread(()->{
            while (true) {

            }
        });
        t2.start();

        Thread t3 = new Thread(()->{
            log.debug("running...");
        });
        t3.start();


        Thread t4 = new Thread(()->{
            synchronized (ThreadStateDemo.class) {
                try {
                    Thread.sleep(10000000); // timed_waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t4.start();

        Thread t5 = new Thread(()->{
            try {
                t2.join();  // waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t5.start();

        Thread t6 = new Thread(()->{
            synchronized (ThreadStateDemo.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("线程t1的状态:{}", t1.getState());
        log.debug("线程t2的状态:{}", t2.getState());
        log.debug("线程t3的状态:{}", t3.getState());
        log.debug("线程t4的状态:{}", t4.getState());
        log.debug("线程t5的状态:{}", t5.getState());
        log.debug("线程t6的状态:{}", t6.getState());
    }
}
