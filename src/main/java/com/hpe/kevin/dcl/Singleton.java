package com.hpe.kevin.dcl;

public final class Singleton {
    // 加上 volatile 可以阻止在创建对象时发生指令重排
//    private volatile static Singleton INSTANCE = null;
    private static Singleton INSTANCE = null;
    private Singleton() {}
    public static Singleton getInstance() {
        // 实例没创建才会进入内部的 synchronized 代码块
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                // 也会有其它线程已经创建了实例, 所以再判断一次
                if (INSTANCE == null) {
                    // new Singleton() 不是原子操作
                    // 字节码层面包含4个步骤
                    // 这4个步骤可能会发生指令重排(赋值操作先于构造方法调用)
                    // 要阻止指令重排, 可以在 INSTANCE 变量声明处加上 volatile
                    // 加了 volatile 后, 给 INSTANCE 赋值, 带写屏障(即构造方法的调用不会在赋值操作之后了)
                    INSTANCE = new Singleton();
                }
            }
        }

        return INSTANCE;
    }
}
