# java 定时任务实现方案

### 1. timer
执行任务时单线程
所以会出现时间不准确、异常等问题    
### 2. 线程池
哪怕一个任务也是多线程执行
### 3. @schedule
默认是单线程执行 但是可以捕获异常
支持cron表达式、自己配置执行所用线程池
### 4. mq

### 5. xxl-job

### 进阶之延时算法原理分析

1. timer 单线程

synchronized 锁 + 小顶堆

2. scheduleExecutor 线程

3. 时间轮算法