package com.dhy.mlife.billingservice.service.impl;

import com.dhy.mlife.billingservice.gateway.cache.itf.CacheI;
import com.dhy.mlife.billingservice.gateway.config.ConfigI;
import com.dhy.mlife.billingservice.gateway.db.itf.MyDb;
import com.dhy.mlife.billingservice.gateway.db.itf.SeataStoragePo;
import com.dhy.mlife.billingservice.service.itf.MyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/11 上午9:59
 */
@Component
public class MyServiceImpl implements MyServiceI {
    @Autowired
    private CacheI cache;
    @Autowired
    private ConfigI config;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MyDb myDb;

    @Override
    public void bizMethod() {
        //redis交互示例
        cache.string$set("sessionid", "name", "lvaolin");
        String name = cache.string$get("sessionid", "name");
        System.out.println(name);
        //--redisTemplate-
        System.out.println(redisTemplate.opsForValue().get("string-sessionid-name"));
        //获取配置文件内容示例
        System.out.println(config.getValue("server.port"));
        System.out.println(config.getValue("server.port","config/app-dev.properties"));
    }
    @Override
    public SeataStoragePo selectByPrimaryKey(Long id){
        return myDb.selectByPrimaryKey(id);
    }
}
