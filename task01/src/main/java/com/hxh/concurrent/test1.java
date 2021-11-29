package com.hxh.concurrent;

import jdk.nashorn.internal.ir.FunctionCall;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
        String[] result = {"执行完毕"};
        FutureTask<String[]> stringFutureTask1 = new FutureTask<String[]>(new Runnable() {
            @Override
            public void run() {
                result[0] +=Thread.currentThread().getName();
            }
        }, result);
        new  Thread(stringFutureTask1).start();
        System.out.println(stringFutureTask1.get()[0]);


        //使用CompletableFuture拿到返回值
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "返回值+" + Thread.currentThread().getName();
        });
        System.out.println(stringCompletableFuture.get());


        //
    }
}
