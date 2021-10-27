package com.study.note.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    public static final long TOKEN_EXPIRES_SECOND = 1800;

    @Autowired
    private StringRedisTemplate redisTpl;

    /**
     * 向redis中设值
     *
     */
    public boolean set(String key, String value) {
        boolean result = false;
        try {
            redisTpl.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向redis中设值，同时设置过期时间
     *
     */
    public boolean set(String key, String value, long time) {
        boolean result = false;
        try {
            redisTpl.opsForValue().set(key, value);
            expire(key, time);
            result =  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置key的过期时间
     *
     */
    public boolean expire(String key, long time) {
        boolean result = false;
        try {
            if(time > 0) {
                redisTpl.expire(key, time, TimeUnit.SECONDS);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
