package com.hxh.concurrent;

import java.util.concurrent.Callable;

public class ThreadA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("ThreadA执行了");
        return "返回值+"+Thread.currentThread().getName();
    }
}
