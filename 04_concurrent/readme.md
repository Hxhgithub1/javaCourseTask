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

- 使用join

``` java
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
```

- 使用CountdownLatch

```java
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
```

- 使用线程池，这里线程池submit的时候也可以传入result(算两种方法，有点重复就不写了)

```java
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
```

- 使用锁

```java
        //使用锁
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.tryLock(1000,TimeUnit.MILLISECONDS);
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
```

**3.（选做）**列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。

**4.（选做）**请思考: 什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪些 因素，对于这些你是怎么理解的?

并发：就是多个业务在同时发生

高并发：就是大量的业务在同时发生，我们如何在现有的硬件条件下用更少的时间更高效的处理这些业务

要考虑的因素：

- 业务处理的时间
- 预测qps的峰值，调整服务器资源，或者进行削峰
- 负载均衡
- 数据备份
- 数据一致性
- 容错机制

**5.（选做）**请思考: 还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决办法。
**6.（必做）**把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。

<img src="img/并发编程导图.jpeg" alt="并发编程导图"  />