package com.hxh.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class test01 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    try {
                        o.wait();
                        System.out.println(o);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                synchronized (o){
                    o.notify();
                }
            }
        }.start();

    }
}
