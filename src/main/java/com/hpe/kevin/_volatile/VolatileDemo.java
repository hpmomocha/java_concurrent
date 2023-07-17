package com.hpe.kevin._volatile;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.VolatileDemo")
public class VolatileDemo {
    static boolean run = false;

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    /**
     *
     */
    private static void test1() throws InterruptedException {
        new Thread(()->{
            while (!run) {
                // 如果 【do something】 中的代码不能读取到主线程对 run 值的修改
                // 那么 t1 线程将一直会运行
                // do something
            }

        },"t1").start();

        Thread.sleep(1000);
        run = true;
    }

    private static void test2() throws InterruptedException {
        new Thread(()->{
            while (!run) {
                // println 方法体内使用了 synchronized
                // 所以 t1 子线程能够读取到主线程修改的 run 的值
                // 所以 test2 方法会在 1s 后停止执行
                System.out.println("running...");
            }

        },"t1").start();

        Thread.sleep(1000);
        run = true;
    }
}
