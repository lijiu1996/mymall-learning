# netty学习

### NIO 与 BIO 处理的本质区别

1.网络请求处理 connect 收到连接请求 -> 在线程池中找到一个空闲线程 -> 线程对该请求进行处理

NIO优化点在于 一个线程管理多个连接 读取可用socket

2. tomcat 核心参数

acceptCount -- 请求队列的长度 如果连接数超过了acceptCount 队列满 新进来的请求会被拒绝

maxConnections -- 任意时刻接受和处理的最大连接数

maxThreads -- 请求处理线程的最大数量
