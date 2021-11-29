package com.hxh.concurrent;

import jdk.nashorn.internal.ir.FunctionCall;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class test1 {
    //在 main 函数启动一个新线程，运行一个方法，拿到这
    //    个方法的返回值后，退出主线程
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //通过实现Callable接口和futuretask配合拿到返回值
        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "返回值+"+Thread.currentThread().getName();
            }
        });
        new  Thread(stringFutureTask).start();
        String s = stringFutureTask.get();
        System.out.println(s);



        //通过实现runnable接口和Futuretask拿到返回值
        StringBuilder result = new StringBuilder();
        FutureTask<StringBuilder> stringFutureTask1 = new FutureTask<StringBuilder>(new Runnable() {
            @Override
            public void run() {
                result.append(Thread.currentThread().getName());
            }
        }, result);
        new  Thread(stringFutureTask1).start();
        System.out.println(stringFutureTask1.get());


        //使用CompletableFuture拿到返回值
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "返回值+" + Thread.currentThread().getName();
        });
        System.out.println(stringCompletableFuture.get());


        //使用join
        StringBuilder sb =new StringBuilder();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sb.append("执行完毕" + Thread.currentThread().getName());
            }
        });
        thread.start();
        thread.join();
        System.out.println(sb.toString());

        //使用CountdownLatch
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sb.append("执行完毕" + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        });
        thread1.start();
        countDownLatch.await();
        System.out.println(sb.toString());

        //线程池
        ExecutorService service = Executors.newSingleThreadExecutor();
        //定义Callable
        Callable<String> callable = new Callable() {
            @Override
            public Object call() throws Exception {

                //返回result
                return "执行完毕" + Thread.currentThread().getName();
            }
        };
        //返回Future，实际上是FutureTask实例
        Future<String> future = service.submit(callable);
        System.out.println(future.get());
        service.shutdown();

        //使用锁
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        System.out.println(lock.getHoldCount());
        System.out.println(sb.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.tryLock(1000,TimeUnit.MILLISECONDS);
                    System.out.println(lock.getHoldCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sb.append("执行完毕" + Thread.currentThread().getName());
                condition.signalAll();
                lock.unlock();
            }
        }).start();
        condition.await();

        //被唤醒的main线程抢占到锁后相当于持有一层锁
        lock.unlock();
        System.out.println(sb.toString());

    }
}
