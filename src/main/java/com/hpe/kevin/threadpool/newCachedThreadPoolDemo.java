package com.hpe.kevin.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * 特点: 核心线程数=0, 最大线程数=Integer.MAX_VALUE
 *      非核心线程的空闲生存时间为60s
 *      线程可以被无限创建
 *      任务执行完毕, 线程池的线程数为0
 */
@Slf4j(topic = "c.newCachedThreadPoolDemo")
public class newCachedThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        // testSynchronousQueue();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        cachedThreadPool.execute(()->{
            log.debug("1");
        });

        cachedThreadPool.execute(()->{
            log.debug("2");
        });

        cachedThreadPool.execute(()->{
            log.debug("3");
        });

        cachedThreadPool.shutdown();
    }

    private static void testSynchronousQueue() throws InterruptedException {
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();

        new Thread(()->{
            try {
                log.debug("putting {}", 1);
                integers.put(1);

                log.debug("putting {}", 2);
                integers.put(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },"t1").start();

        Thread.sleep(1000);

        new Thread(()->{
            try {
                log.debug("taking {}", 1);
                integers.take();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },"t2").start();

        new Thread(()->{
            try {
                log.debug("taking {}", 2);
                integers.take();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },"t3").start();
    }
}
