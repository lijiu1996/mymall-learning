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


### 入门操作

### mq如何实现消息可靠投递

发布者发送消息  返回ack 收不到ack无线重试
broker收到消息 推送/拉去给消费者 消费者消费完成 返回ack 收不到ack不删除消息

