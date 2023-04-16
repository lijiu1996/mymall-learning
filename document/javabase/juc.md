# 高并发学习

### JDK 线程池机制学习
cacheThreadPool 与 同步移交队列 SynchronousQueue

横向对比 Complete 与 ListeningFuture
两者都是对Future功能的扩展 以支持实现异步操作下的结果回调和组合功能
CompleteableFuture 稍微简单一些

ComplateableFuture supplayAsync方法作用
异步起一个新线程执行该future的方法 并返回future

CompleteableFuture 如何实现异步回调
thenapply机制

thenapply 和 thencompose的区别
感觉上也没啥区别 thenapply是给一个结果执行一段逻辑
thencompose是传递一个completeFuture

异步调用的组合类型 零依赖 1依赖 二依赖 三依赖
实现回调的话就是在当前completeFuture后面再组合一个函数调用

### Future机制的实现原理

completeFuture多线程实现原理要点分析


### JDK 线程池使用最佳实践

1. 避免直接使用Executors生成线程池 参数配置不当会导致OOM
2. 检测线程池的运行状态, 推荐使用Spring Actuator组件
3. 使用多个线程池而不是共用一个(从死锁的角度去考虑)
4. 给线程池命名 guava ThreadFactoryBuilder
5. 集成动态线程池框架 实现线程池参数可动态化设置，并集成监控和报警功能
6. 线程池与ThreadLocal共用 导致ThreadLocal读取的是旧值 -- 阿里开源TransmittableThreadLocal

美团动态线程池实践原理

### 线程池 vs 异步

理解cpu密集型task 和 io密集型task 像文件IO 网络IO都可以认为是io密集型task
cpu提交了一个指令后是在进行等待 会执行线程切换

线程池和异步的概念理解与区分
多线程操作适用于密集计算的同时进行 充分利用cpu 并行执行加快时间 最终进行结果归并
多线程还有一种适用场景 就是可以实现并发响应 两个方法调用之间相互隔离 防止一个链路调用过长 别人等待的场景
异步适用于io场景 避免无效阻塞

线程池在进行耗时操作 比如db处理以及rpc调用的时候 (占用cpu嘛?)
由于耗时较久 大量请求过来 会 迅速把任务队列打满

针对这种场景该如何进行优化
1. 使用多个线程池 对耗时任务做隔离

问题二 为什么cpu只有10几个 却可以支持几百个线程池
底层自动进行切换 发起网络请求或者io请求 或者io时间片到期 cpu自动切换到下一个线程

### 实际生产问题案例

fullGC 场景一 采用了固定大小线程池 任务队列无上限
在该线程池中提交异步任务 如推送数据到kafka 如果因为网络原因 导致推送速度变慢
高并发访问下 线程池的队列就会越积越大 最终导致full gc

解决方案 使用有界队列 同时增加kafka的消费能力(批量 )

场景二 采用了固定大小线程池 任务队列无上限
线程池中的线程全部死锁 task处理不了 线程池任务队列中累积的任务越积越多 最终full gc

总结 线上环境cpu过高的几个原因
1. 线程空转 自旋等待或者死循环
2. 死锁
2. 密集计算
3. 内存不足 频繁进行fullgc 
4. 系统大量线程切换

场景三 内存爆满导致cpu产生连环问题 
内存撑满 Full gc频繁 导致大量的STW  cpu飙高 反过来导致大量线程拿不到cpu被阻塞 最终服务挂了

场景四 产生了大量被引用无法回收的对象 导致fullgc

### 生产环境如何实现监控

线程池监控框架 -- 任务排队耗时、任务执行耗时 
-- 实现方案 装饰器模式包装Runnable Task

服务监控 -- 调用量、超时量、错误日志

中间件监控 -- MySQL、redis、mq 慢查询监控

JVM监控 -- jstack dump 线程快照排查死锁

jstack命令介绍
生成线程快照,即当前JVM内每一条线程正在执行的方法堆栈的集合,
定位线程出现长时间停顿的原因,如死锁、死循环、请求外部资源时间过长

top -H -p 查看系统线程情况

jmap命令 
对jvm堆内存进行dump 生成快照文件
jmap -histo
分析JVM进程的堆内存占用 以及所有对象的概况

jprofile 内存分析工具 对dump快照进行分析
