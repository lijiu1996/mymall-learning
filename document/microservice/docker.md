# docker 安装部署

### 1. 卸载之前安装过docker

yum remove docker \
docker-client \
docker-client-latest \
docker-common \
docker-latest \
docker-latest-logrotate \
docker-logrotate \
docker-selinux \
docker-engine-selinux \
docker-engine \
docker-ce

### 2. 安装yum插件

yum install -y yum-utils device-mapper-persistent-data lvm2

### 3. 添加阿里云docker镜像仓库

yum-config-manager \
--add-repo \
https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

yum makecache fast

-- 解决阿里云网址无法解析问题
查询如何配置linux网卡 设置dns

### 4. 安装docker

查看所有yum 软件版本
yum list docker-ce --showduplicates | sort -r

安装
yum install -y docker-ce

关闭防火墙
systemctl stop firewalld

禁止开机启动防火墙
systemctl disable firewalld

docker开机自启 
systemctl start docker
systemctl enable docker

### 5. docker 常用命令

拉取
docker pull xxx:tag

保存到磁盘
docker save -o name image
docker rmi image
导入
docker load -i nginx.tar

docker run 
docker rm 删除容器
docker logs 查看容器运行日志
docker ps

启动容器 es为例
docker run --name containerName -p 80:80 -d nginx
-p 将宿主机与容器端口进行映射 前面是宿主机端口 后面是容器端口
-d 后台运行

docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag

进入容器执行命令
docker exec -it mn bash

数据卷挂载 docker -v 宿主机数据卷路径/容器内路径

-- privileged=true 表示开放root权限

容器内没有vi怎么修改文件
sed -i -e 's#Welcome to nginx#传智教育欢迎您#g' -e 's#<head>#<head><meta charset="utf-8">#g' index.html

容器自定义镜像 dockerfile
* 基础环境运行时库
* 定义环境变量
* 安装
* 程序入口

文件例子
FROM java:8-alpine
COPY ./app.jar /tmp/app.jar
EXPOSE 8090
ENTRYPOINT java -jar /tmp/app.jar

基于dockerfile构建镜像命令
docker build -t image:tag .

### 6. docker-compose

docker compose 将容器启动命令整合到yml中 实现一键启动多个

安装
curl -L https://github.com/docker/compose/releases/download/1.29.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

### 7. 安装gui工具 portainer


docker pull portainer/portainer

mkdir -p /home/portainer/data /home/portainer/public

docker run -p 9000:9000  --name portainer \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /home/portainer/data:/data \
-d portainer/portainer-ce

问题一
注意安装ce版本 且不要随便汉化

### 8. docker高级篇

网络相关知识(搭建集群需要)

docker network create es-net
docker run -d --name es 
        -e ”ES_JAVA_OPTS=-Xms512m -Xmx512m“
        -e "discovery.type=single.mode"
        -V

docker容器内安装命令 
apt-get update
apt-get install vim
apt-get install iputils-ping

### 7. 心得记录

### docker 安装redis 完整命令

1.创建目录
mkdir -p /home/redis/conf
创建文件
touch /home/redis/conf/redis.conf
docker run --name my-redis -d -p 6379:6379 redis

2.启动容器
docker run \
--name redis \
-p 6379:6379 \
--log-opt max-size=1m \
-v /home/redis/data:/data \
-v /home/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis:7 redis-server /etc/redis/redis.conf 

3.编辑配置文件
编辑redis.conf

### docker 安装 elasticsearch 完整命令

docker network create es-net

docker run --name elasticsearch -p 9200:9200  -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms84m -Xmx512m" \
--net es-net \
-v /home/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /home/elasticsearch/data:/usr/share/elasticsearch/data \
-v /home/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.17.9

配置elasticsearch yml