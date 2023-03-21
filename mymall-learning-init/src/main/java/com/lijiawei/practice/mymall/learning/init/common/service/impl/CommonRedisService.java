package com.lijiawei.practice.mymall.learning.init.common.service.impl;

import com.lijiawei.practice.mymall.learning.init.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CommonRedisService implements RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public <T> void set(String key, T value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> void mset(Map<String, T> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public <T> T get(String key) {
        ValueOperations<String, T> operations = (ValueOperations<String, T>) redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }


    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    @Override
    public Boolean expire(String key, long time, TimeUnit timeUnit) {
        return redisTemplate.expire(key,time,timeUnit);
    }

    // 返回0代表永久有效
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public <T> T hGet(String key, String hashKey) {
        HashOperations<String, String, T> operations = redisTemplate.opsForHash();
        return operations.get(key, hashKey);
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time, unit);
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    // hmget功能
    @Override
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time, TimeUnit timeUnit) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time, timeUnit);
    }

    @Override
    public void hSetAll(String key, Map<String, ?> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    @Override
    public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    // get all
    @Override
    public <T> Set<T> sMembers(String key) {
        return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    // 移除值为value的 返回移除的个数
    @Override
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    // ===================sortedSet===========================
    @Override
    public Boolean zSetAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    public Double zSetInc(String key, Object value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    @Override
    public <T> Set<T> zSetGetByRange(String key, long start, long end) {
        return (Set<T>) redisTemplate.opsForZSet().range(key, start, end);
    }

    @Override
    public long zSetRemove(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key,value);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public <T> T lIndex(String key, long index) {
        ListOperations<String, T> operations = (ListOperations<String, T>) redisTemplate.opsForList();
        return operations.index(key, index);
    }

    @Override
    public <T> List<T> lGetByRange(String key, long start, long end) {
        ListOperations<String, T> operations = (ListOperations<String, T>) redisTemplate.opsForList();
        return operations.range(key, start, end);
    }

    @Override
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }

    @Override
    public Long lLeftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Long lPushAll(String key, List<?> dataList) {
        return redisTemplate.opsForList().rightPushAll(key, dataList);
    }

    // 根据值进行遍历删除
    @Override
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     *  查找redis时间戳
     *      执行lua脚本 EVAL "local a=redis.call('TIME') ;return (a[1]*1000000+a[2])/1000 " 0
     */
    public long eveLuaForTime()
    {
        try {
            String script = "local a=redis.call('TIME') ;return (a[1]*1000000+a[2])/1000 ";
            DefaultRedisScript redisScript = new DefaultRedisScript();
            redisScript.setResultType(Long.class);
            redisScript.setScriptText(script);
            RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
            Object execute = redisTemplate.execute(redisScript, stringSerializer, stringSerializer, new ArrayList<>(), "0");
            return (Long) execute;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * pb5 原理学习-redis
     *      redis支持事务 但是这个事务和mysql不一致 不能回滚 只是用于批量操作
     *      使用multi开启事务 使用exec执行事务
     *      事务开启前可以进行数据监听 监听的值如果multi后exec前发生变化
     *      说明有其他线程已经修改了数据 就会导致事务执行失败
     */
    //开启事务
    public void openTransaction(){
        redisTemplate.multi();
    }

    //执行事务
    public void commitExecute(){
        redisTemplate.exec();
    }


}
