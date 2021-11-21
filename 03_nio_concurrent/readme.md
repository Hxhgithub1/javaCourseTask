#  作业

1.（必做）整合你上次作业的 httpclient/okhttp。
2.（选做）使用 Netty 实现后端 HTTP 访问（代替上一步骤）。
3.（必做）实现过滤器。
4.（选做）实现路由。
5.（选做）跑一跑课上的各个例子，加深对多线程的理解。
6.（选做）完善网关的例子，试着调整其中的线程池参数。



#  说明

作业的1,3,4题的实现均在task01/src/main/java/com/hxh/nio目录下

其中：

netty/NettyHttpServer ：是启动类

netty/HttpHandler：中整合了过滤器和路由选择功能

HttpServer01,HttpServer02：是路由选择监听的两个端口的实现

OkHttpClient：是okhttp实现的http访问

