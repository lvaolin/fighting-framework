package com.dhy.demo.spring.redis.service;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Project parent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/6/11 5:39 下午
 */
@Component
@CacheConfig(cacheNames = "cacheName1")
public class CacheServiceImpl implements ICacheService{
    @Override
    @Cacheable(cacheNames = "cacheName1",key ="#name" )
    public Object setCache1(String name) {
        return System.currentTimeMillis();
    }

    @Override
    @Cacheable(cacheNames = "cacheName200",key ="#name" )
    public Object setCache2(String name) {
        return System.currentTimeMillis();
    }

    @Override
    @Caching(put = {@CachePut(key ="#name" )})
    public Object updateCache1(String name) {
        return System.currentTimeMillis();
    }

    @Override
    @CacheEvict(key ="#name" )
    public Object deleteCache1(String name) {
        return true;
    }
}
