package com.hpe.kevin.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 使用场景: 适用于任务量已知, 相对耗时的任务
 * 特点:    核心线程数=最大线程数
 *         阻塞队列是无界队列, 可以放任意数量的任务
 */
@Slf4j(topic = "c.newFixedThreadPoolDemo")
public class newFixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        fixedThreadPool.execute(()->{
            log.debug("1");
//            throw new RuntimeException();
        });

        fixedThreadPool.execute(()->{
            log.debug("2");
        });

        fixedThreadPool.execute(()->{
            log.debug("3");
        });

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) fixedThreadPool;
        log.debug("current core pool size: {}", threadPool.getCorePoolSize());
        threadPool.setCorePoolSize(2);
        log.debug("new core pool size: {}", threadPool.getCorePoolSize());

        fixedThreadPool.shutdown();
        fixedThreadPool.shutdownNow();
    }
}
