**1.（选做）**尝试使用 Lambda/Stream/Guava 优化之前作业的代码。

**2.（选做）**尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。

**3.（选做）**根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。

**4.（选做）**根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。

**5.（选做）**基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化

**6.（必做）**基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

用户表:user_id,user_name,user_gender,user_age,user_phone,user_create_time

```mysql
create table `user`(    
	`user_id` bigint Not Null AUTO_INCREMENT COMMENT '用户id',
    `user_name` varchar(100) NOT NULL COMMENT '用户名',
    `user_gender` bool NOT NULL COMMENT '用户性别',
	`user_age` varchar(100) NOT NULL COMMENT '年龄',
    `user_phone` int NOT NULL COMMENT '手机',
    `user_create_time` datetime NOT NULL COMMENT '创建时间',
    primary key (`user_id`) using btree
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户';
```



商品：commodity_id,commodity_name,commodity_price,commodity_category,commodity_nums,store_id,

```mysql
create table `commodity`(    
	`commodity_id` bigint Not Null AUTO_INCREMENT COMMENT '主键id',
    `commodity_name` varchar(100) NOT NULL COMMENT '商品名',
    `commodity_price` int NOT NULL COMMENT '商品价格',
	 `commodity_category` varchar(100) NOT NULL COMMENT '商品种类',
    `commodity_nums` int NOT NULL COMMENT '商品数量',
    `store_id` bigint NOT NULL COMMENT '店铺id',
    primary key (`commodity_id`) using btree
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商品';
```

订单：order_id,commodity_id,user_id,order_create_time,order_pay_time,order_deliver_time

```mysql
create table `order`(    
	`order_id` bigint Not Null AUTO_INCREMENT COMMENT '订单id',
    `commodity_id` bigint NOT NULL COMMENT '商品id',
    `user_id` bigint NOT NULL COMMENT '用户id',
	`order_create_time` datetime NOT NULL COMMENT '订单创建时间',
    `order_pay_time` datetime COMMENT '订单支付时间',
    `order_deliver_time` datetime COMMENT '订单发货时间',
    primary key (`order_id`) using btree
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单';
```

店铺：store_id,store_name,store_category,store_level

```mysql
create table `store`(    
	`store_id` bigint Not Null AUTO_INCREMENT COMMENT '店铺id',
    `store_name` varchar(100) NOT NULL COMMENT '店铺名',
    `store_category` varchar(100) NOT NULL COMMENT '店铺类别',
	`store_level` int NOT NULL COMMENT '店铺等级',
    primary key (`store_id`) using btree
)ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='店铺';
```

**7.（选做）**尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。

**8.（选做）**基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。

**9.（选做**）尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。

**10.（选做）**模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。

**11.（选做）**对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。