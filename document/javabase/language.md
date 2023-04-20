# Jav语言特性学习

### Exception学习
区分Throwable Error Exception RuntimeException 继承体系
你真的会自定义Exception吗?

### 函数式接口 学习

### optional 学习

1. 使用orElse 代替 get方法进行变量获取,避免npe
2. 使用orElseGet 代替 orElse 可以避免值的计算代价
3. 抛出异常用 orElseThrow
4. 使用ifPresentOrElse 代替if判断 执行业务逻辑
5. or方法代替orElse方法 直接返回Optional对象而不是包装在内部的值

### 日志打印学习

1. 日志级别

ERROR 无法容忍的错误
WARN 触发了异常但不影响系统运行
INFO 记录系统关键信息 保留系统正常工作期间关键运行指标 比如初始化配置 业务状态彼岸花信息
DEBUG 将各类详细信息记录到DEBUG里 参数返回值等等调试要用到的信息
TRACE 更详细的跟踪信息

2. 日志格式

有明确的上下文信息及参数详情就好 比如[无权操作] userid:[xxx]
[id, module, params, content]

3. 什么时候该打印日志

系统初始化
重要方法入口
业务流程与预期不服
系统核心流程
异常

### stream 学习

1. stream所有操作只会在终止操作的时候进行执行
2. Collector高级用法
    比如如何找到数值最大的对象
    如何进行多级分组
3. 自定义collector收集器 -- 深入分析了Collector的源码设计 nb
4. 如何在lambda表达式中修改外部变量的值 比方说stream 次数统计等

### Java 集合学习

map.put compute putIfAbsent computeIfAbsent 区分

put 设置一个key为某个value 返回设置之前的值
compute 根据当前key 计算得到下一个key的值 并设置 返回覆盖后的值