# spring 学习

### SpringIOC自动装配

@Qualifier 指定名字  优先级高于 @Primary

@Autowried 与 @Resource 的区别
Autowired 根据类型进行匹配 如果类型重复 可以用@Qualifier进行进一步指定
Resource  根据名字进行匹配 名字匹配不到根据类型进行匹配

### SpringAOP应用之AOP

添加自定义注解
切面自动扫描自定义注解 植入代理逻辑
自定义注解如何添加代理逻辑 注解属性配置自定义的类class

类型匹配语法
*
..
+

切点表达式扩展之within args this
within 指定类型内的方法
this
args 匹配参数
target 匹配当前执行方法的目标对象

### Spring 扩展学习之全局异常处理

原本同步@ControllerAdvice实现的全局异常处理存在一定问题
即只能处理从Controller抛出的异常 不能处理从异步调用、定时调用或工具类中抛出的异常

解决方案 自定义AOP代理实现异常捕获
@AfterThrowing问题 异常在经过AfterThrowing处理后还会进一步向外抛出

### Spring原理学习

@Conditional 条件注入

### Mybatis高级技巧

1. 返回Map使用@MapKey可以自动提取对象属性作为key

2. mysql 字符串也可以进行 between 操作 比较范围不仅仅限于数值 还有时间

3. foreach 多个表
   ```
   <foreach collection="list" item="Name" index="index" open="(" close=") t">
            <if test="index != 0">
                union all
            </if>
            select * from ${Name}
   </foreach>
   ```
