#  作业

**1.（选做）**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

**2.（选做）**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

**3.（选做）**如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

**4.（必做）**根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

- SerialGC：其年轻代使用标记-复制算法，老年代使用标记-清除-整理算法。是串行GC只能单线程工作，在执行gc的时候会stw。由于其是串行的所以停顿时间较长，不适用于大型项目。

- ParallelGC：年轻代和老年代的垃圾回收都会触发 STW 事件。 在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。是并行GC能够充分利用cpu的多个核心，所以停顿时间会缩短，能够增加吞吐量

- CMSGC(Mostly Concurrent Mark and Sweep  Garbage Collector)：对年轻代采用并行 STW 方式的 mark-copy (标记-复制)算法，对老年代主要使用并发 mark-sweep ( 标记-清除)算法。并发标记的大部分阶段都是gcxian线程和并发线程同时执行的，只有初始标记和最终标记会stw,这样大大缩短了老年代gc的停顿时间。
- G1GC：
- ZGC/Shenandoah GC





**5.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。
**6.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [ http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub。

链接：./okhttp.java

