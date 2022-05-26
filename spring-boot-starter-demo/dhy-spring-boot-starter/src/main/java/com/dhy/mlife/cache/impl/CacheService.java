package com.dhy.mlife.cache.impl;

import com.dhy.mlife.cache.impl.mapper.CacheTableMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CacheService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CacheTableMapper cacheTableMapper;

    public void set(String key, String value) {
        //存储string
        redisTemplate.opsForValue().set(key, value);

        //存储对象
        CacheTablePo cacheTablePo = new CacheTablePo();
        cacheTablePo.setKey(key);
        cacheTablePo.setValue(value);
        redisTemplate.opsForHash().put("sessionid","userForm",cacheTablePo);

        CacheTablePo cacheTablePoFromRedis = (CacheTablePo)redisTemplate.opsForHash().get("sessionid", "userForm");
        log.info(cacheTablePoFromRedis.toString());

        //存储DB
        cacheTableMapper.set(key, value);
    }
}
