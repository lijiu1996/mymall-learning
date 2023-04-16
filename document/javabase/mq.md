# 消息队列学习

### 0.部署kafka 
docker pull wurstmeister/kafka
docker pull wurstmeister/zookeeper

docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper

docker run -d --name kafka \
-p 9092:9092 \
-e KAFKA_BROKER_ID=0 \
-e KAFKA_ZOOKEEPER_CONNECT=192.168.254.11:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.254.11:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092  \
wurstmeister/kafka

使用docker-compose 安装kafka
docker-compose -f docker-compose-single-kafka.yml up -d

### 入门操作

### 0.mq使用场景

场景: 消息系统/日志指标收集

作用 
1. 解耦
生产者将data推送到队列中 消费者根据业务需求去订阅对应的topic 即使消费者出现变化 只是增减订阅不涉及代码改动

2. 异步 
异步数据传递 加快响应/对比rpc 是同步地进行方法调用,二者适用于不同场景
异步处理引入的问题 如果其中的一个异步环节出现了异常 如何保证最终的正确性 -- 分布式事务

3. 削峰

4. 引入mq后带来的问题
中间件高可用 -- kafka天然的patition replica机制保证高可用(重新选主) 
消息重复消费 -- 微服务幂等性问题
消息丢失问题 -- 严格执行ack 与 落盘机制
消费顺序问题 -- 根据业务做路由
消息广播问题 -- 将消息同时写入多个topic

消息匹配问题 -- 实现规则引擎?
消息超时问题 -- 下单用户15分钟未支付,自动取消订单 
            延迟队列 + 时间轮算法
数据一致性问题 -- 如何保证本地事务成功同时 消息成功被消息队列接受
               分布式事务

### 1.mq如何实现消息可靠投递 -- 消息丢失问题

消息丢失的场景 1. 生产者发送时丢失
            2. mq收到消息写入磁盘之前丢失
            3. 硬盘坏了丢失消息
            4. mq挂了丢失消息

发布者发送消息  返回ack 收不到ack无限重试 
避免重复 broker会对消息进行去重

broker收到消息 推送/拉取给消费者 消费者消费完成 返回ack 收不到ack无限重试不删除消息

consumer需要进行幂等处理

消息丢失机制 -- 异步刷盘改为同步刷盘
           -- raid10磁盘阵列
           -- 不要采用自动ack机制

### 1. 顺序消费问题
产生顺序消费问题的根源 1. 多个消费者消费同一个partition
                  2. 消费者起多线程进行消费

解决方案 根据消息的内容做分类 分类到内存队列中 然后再用多线程进行消费

### 2. !!mq的数据一致性问题

消息如果是同步发送 不需要考虑该一致性问题
消息如果是异步发送 采用回调机制更新本地消息表, 然后定时任务进行判断 重试
(问题是已经返回给客户端成功了, 会不会造成用户困惑)

### 2. rabbitmq 原理

六种工作模式
helloworld
working queue 任务会负载给多个消费者 每个消费者的消息不同
发布订阅       生产者会把任务委派给交换机 交换机对消息进行拷贝 拷贝进入不同队列 保证每一个订阅的消费者都能收到消息

rabbitmq 死信队列机制
死信 -- 消费者处理失败/队列达到上限/ttl

### 3. kafka原理

为什么kafka速度这么快？
顺序读写磁盘
内存直接由操作系统管理而不是jvm
零拷贝机制
数据批量处理写入

分区的概念 -- 提高了吞吐量 支持同一时刻并发写入多个分区

### 4. kafka 一些基本概念

topic 用于指定消息的路由
partition 每个topic由多个partition组成 一个partition可以理解为一个文件
replication 对partition的冗余复制

数据生产过程
    对于一条消息 必须填写topic value
    如果partition没有填 a. key有填写  相同key进入相同partition
                       b. key没填    根据算法
                
数据消费过程
    利用消费者组实现消息的单播和多播,单播消息vs多播消息
    消费者组中的消费者消费哪些消息其实是与partition挂钩的,一个partition消息只能被一个消费者消费
    
消费者组保证顺序消费的概念 维护一个消费的偏移量  
    维护了一个comsumer-offset topic
       
### 5. kafka 命令行使用

创建topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 
--partitions 1 --topic topic-name

生产
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-name
    
问题 - 是发送给该topic的哪个分区呢？partition选择机制

消费消息
kafka-console-consumer --topic test --bootstrap-server 192.168.5.57:9092 --from-beginning

问题 - 没有指定partition

单播消息与多播消息命令
--consumer-property group.id=

如何往kafka集群发送消息

kafka消息发送 同步发送和异步发送的概念

发送消息的ack配置 0 1 -1 需要大于配置值以上的foller返回ok

自动提交和手动提交offset的概念 自动提交 消息poll下来以后直接提交offset
                           手动提交 消费完成以后提交offset

关于拉取消息的一些细节配置 
    如果没有la

消费者的健康状态检查

消费者指定主题消费 

消费者进行回溯消费
                    
### 6. kafka 集群配置                

### 7. kafka 源码设计经验总结

* 高可用

leader选举机制
冗余副本机制
ISR机制

* 高