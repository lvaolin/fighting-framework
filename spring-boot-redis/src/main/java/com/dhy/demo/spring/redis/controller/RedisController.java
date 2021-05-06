package com.dhy.demo.spring.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @RequestMapping("/getSubmitToken")
    String getToken(){
        String submitToken = UUID.randomUUID().toString();
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(submitToken,submitToken,3600, TimeUnit.SECONDS);
        return submitToken;
    }

    @RequestMapping("/submit")
    String submit(){
        System.out.println("submit success!!!");
        return "success";
    }



}
