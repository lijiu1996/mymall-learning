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

2. 异步地在两个系统之间传递数据/对比rpc 是同步地进行方法调用,二者适用于不同场景

异步处理引入的问题 如果其中的一个异步环节出现了异常 如何保证最终的正确性 -- 分布式事务
 
3. 流量控制

引入mq后带来的问题
考虑消息不重复消费
考虑消息不丢
考虑顺序消费? 如何保证
考虑消费者下游报错产生的一致性问题

### 1.mq如何实现消息可靠投递

发布者发送消息  返回ack 收不到ack无限重试 
避免重复 broker会对消息进行去重

broker收到消息 推送/拉取给消费者 消费者消费完成 返回ack 收不到ack无限重试不删除消息

consumer需要进行幂等处理

消息丢失机制 -- 异步刷盘改为同步刷盘
           -- raid10磁盘阵列
           -- 不要采用自动ack机制

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
                       
### 6. kafka 集群配置                

