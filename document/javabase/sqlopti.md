### mysql 查询优化问题

### mysql Explain 深度解析

type列 查询类型 
const(唯一非空索引) 
eq_ref  ref(没有唯一的要求) ref_or_null(没有非空的要求)
index_merge 索引联合统一回表
range 
index 遍历索引树
all   遍历记录树

extra列 
using index -- 覆盖索引
using index condition -- 索引下推
using where -- 和using condition相对 全部放在server层做条件判断
    
using MRR
    对索引查询结果进行排序 统一进行回表 把随机id优化为顺序io

using join buffer 

using union 
using temporary -- 临时表 性能很低
using filesort -- 

什么是索引下推
    把server层负责的逻辑判断下推到引擎层
    引擎层就做条件判断过滤业务逻辑

### 1. 带order by 的分页查询优化问题

如果需要根据某一个字段做深分页  
原理 会查询所有id,time 然后根据time进行排序, 在根据id去回表

问题一 为什么using filesort 就是深分页会导致查询时间比浅分页长这么多

解决方案一 将所有查询字段都添加到索引中 实现覆盖索引
解决方案二 

可以使用join的方式 先建索引 根据该索引查出指定记录 然后join上其他字段
这个解决了先进性 select 后进行 order by的问题

解决方案三 如果页数太深 解决方案二还可以进行优化
就是不通过页数去做筛选 而是直接传一个值 这样就可以直接走索引的过滤 又是进一步优化
不过就是每次查询的时候需要带一个where条件


### 2. mysql索引失效问题

模型数空运最快

### 3. fileSort优化问题

最左前缀匹配原则 、 覆盖索引等角度去考虑

### 4. 一条查询语句的执行顺序 

先进行order by 还是先进行where ？

先进性 where 然后 select 然后order by