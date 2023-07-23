package com.hpe.kevin.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 使用场景: 希望多个任务排队执行。
 * 特点: 线程数固定为1, 任务数多余1时, 会放入无界队列排队。
 *      任务执行完毕, 唯一的线程也不会被释放。
 */

/**
 * 和自己创建一个线程来执行任务的区别:
 * 自己创建一个单线程串行执行任务, 如果任务执行失败而终止则没有任何补救措施, 而线程池还会创建新的一个线程来保证线程池的正常工作.
 */

/**
 * newSingleThreadExecutor 线程个数始终为1, 无法修改。
 *      因为 newSingleThreadExecutor 的返回值为 FinalizableDelegatedExecutorService 类型
 *      FinalizableDelegatedExecutorService 应用的是装饰器模式, 只对外暴露 ExecutorService 接口的方法,
 *      因此不能调用 ThreadPoolExecutor 中特有的方法
 * 而 newFixedThreadPool(1) 这种方式创建的线程池, 对外暴露的是 ThreadPoolExecutor 对象
 *      后期可以通过将 newFixedThreadPool 强制转换成 ThreadPoolExecutor 类型, 然后调用 setCorePoolSize 等方法来修改核心线程数
 */
@Slf4j(topic = "c.newSingleThreadPoolDemo")
public class newSingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    }
}
