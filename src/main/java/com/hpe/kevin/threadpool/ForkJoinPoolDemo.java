package com.hpe.kevin.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new MyRecursiveAction(0, 1000));

        // 阻塞当前线程, 直到ForkJoinPool 中所有任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        // 关闭线程池
        forkJoinPool.shutdown();
    }
}

class MyRecursiveTask extends RecursiveTask<Integer> {

    @Override
    protected Integer compute() {
        return null;
    }
}

class MyRecursiveAction extends RecursiveAction {
    private static final int MAX = 20;

    private int start;

    private int end;

    public MyRecursiveAction(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "-i的值" + i);
            }
        }

        int middle = (start + end) / 2;
        MyRecursiveAction left = new MyRecursiveAction(start, middle);
        MyRecursiveAction right = new MyRecursiveAction(middle, end);
        left.fork();
        right.fork();
    }
}
