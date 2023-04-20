# 泛型语言特性学习

### 泛型实现本质

类型擦除
桥接方法 编译器底层会自动实现一个输入类型和输出类型替换为Object的方法 

类型擦除导致的java语言自身的衍生问题
1. 泛型不支持基本类型
2. 不支持对象泛型检测 obj instance of List<Integer>
3. 不能实例化泛型对象 new T
4. 不能实例化泛型数组 new T[]

### 泛型衍生问题

1. 集成两个泛型类时会报方法冲突

比如实现泛型类的equals(T ) 方法, 桥接方法为equals(Object ) 与Object类中的方法冲突了 不允许该写法

解决方案 -- 如真必须实现这个功能 换一个名字为其他的方法即可

2. 泛型数组

ArrayList<Integer>[] arr 

这种要注意 数组是没有约束能力的 
任何ArrayList对象都可以往该数组里面放 

3. 如何创建一个泛型对象 或者泛型数组

传类型 T 或者传对象T

扩展: 如何判断一个对象是否为某一个Class<T>的实例 Class.isInstance

反射扩展1 - 通过GetGenericSupperClass or GetGenericInterface
转化为ParameterizeType 然后GetTypeArguments 获取到T的类型

4. 泛型和重写的组合限制

抓住两个关键点

5. 泛型与通配符 PECS原理

核心是做函数参数中泛型对象的传递

### Java 反射学习

class.isInstance
class.isAssignableFrom
instanceof 的区别


反射至高扩展
如何将每一次的反射调用转化为方法引用 以提高性能 
MethodHandle Lookup 的使用
LambdaMetaFactory将lambda表达式转化为匿名内部类(字节码)
绕过反射调用方法
