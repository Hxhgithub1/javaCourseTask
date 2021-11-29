**1.（选做）**把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。

示例代码已运行

**2.（必做）**思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。
一个简单的代码参考: [ https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301 ](https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301)/src/main/java/java0/conc0303/Homework03.java

- 通过实现Callable接口和futuretask配合拿到返回值

``` java
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
```

- 通过实现runnable接口和Futuretask拿到返回值

```java
String[] result = {"执行完毕"};
FutureTask<String[]> stringFutureTask1 = new FutureTask<String[]>(new Runnable() {
    @Override
    public void run() {
        result[0] +=Thread.currentThread().getName();
    }
}, result);
new  Thread(stringFutureTask1).start();
System.out.println(stringFutureTask1.get()[0]);
```

- 使用CompletableFuture拿到返回值

```java

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "返回值+" + Thread.currentThread().getName();
        });
        System.out.println(stringCompletableFuture.get());
```

**3.（选做）**列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。

工具类



**4.（选做）**请思考: 什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪些 因素，对于这些你是怎么理解的?
**5.（选做）**请思考: 还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决 办法。
**6.（必做）**把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。

<img src="img/并发编程导图.jpeg" alt="并发编程导图"  />s