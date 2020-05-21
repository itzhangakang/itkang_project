package com.itkang.itkang_utils.utis.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2020/1/13 16:43
 * @explain 使用Redis实现分布式锁
 */
@Component
public class RedisLock {

    /**
     * redis加锁
     */
    String value = System.currentTimeMillis() + 10000 + "";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     */
    public boolean lock(String key, String value) {
        //setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
        //即：在判断这个key是不是第一次进入这个方法
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //第一次，即：这个key还没有被赋值的时候
            return true;
        }
        String current_value = redisTemplate.opsForValue().get(key);
        if (!current_value.equals("") && Long.parseLong(current_value) < System.currentTimeMillis()) {
            String old_value = redisTemplate.opsForValue().getAndSet(key, value);
            if (!old_value.equals("") && old_value.equals(current_value)) {
                return true;
            }
        }
        return false;
    }

    //解锁
    public void unlock(String key, String value) {
        try {
            if (redisTemplate.opsForValue().get(key).equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
