# 消息队列学习

### 部署kafka 
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

### 1.mq如何实现消息可靠投递

发布者发送消息  返回ack 收不到ack无限重试 
避免重复 broker会对消息进行去重

broker收到消息 推送/拉取给消费者 消费者消费完成 返回ack 收不到ack无限重试不删除消息

consumer需要进行幂等处理

消息丢失机制 -- 异步刷盘
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
数据批处理功能

