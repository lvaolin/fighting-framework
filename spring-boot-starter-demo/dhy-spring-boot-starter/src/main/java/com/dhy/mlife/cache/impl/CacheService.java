package com.dhy.mlife.cache.impl;

import com.dhy.mlife.cache.impl.mapper.CacheTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/21 上午10:24
 */
@Component
public class CacheService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CacheTableMapper cacheTableMapper;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        cacheTableMapper.set(key, value);
    }
}
