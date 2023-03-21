package com.lijiawei.practice.mymall.learning.init.common.service.impl;

import com.lijiawei.practice.mymall.learning.init.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        return null;
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
        return null;
    }

    @Override
    public Long decr(String key, long delta) {
        return null;
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return null;
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        return null;
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {

    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        return null;
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        return null;
    }

    @Override
    public void hSetAll(String key, Map<String, ?> map) {

    }

    @Override
    public void hDel(String key, Object... hashKey) {

    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return null;
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return null;
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return null;
    }

    @Override
    public Set<Object> sMembers(String key) {
        return null;
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return null;
    }

    @Override
    public Long sAdd(String key, long time, Object... values) {
        return null;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return null;
    }

    @Override
    public Long sSize(String key) {
        return null;
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return null;
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long lSize(String key) {
        return null;
    }

    @Override
    public Object lIndex(String key, long index) {
        return null;
    }

    @Override
    public Long lPush(String key, Object value) {
        return null;
    }

    @Override
    public Long lPush(String key, Object value, long time) {
        return null;
    }

    @Override
    public Long lPushAll(String key, Object... values) {
        return null;
    }

    @Override
    public Long lPushAll(String key, Long time, Object... values) {
        return null;
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return null;
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
}
