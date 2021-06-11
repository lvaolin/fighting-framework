package com.dhy.demo.spring.redis.controller;

import com.alibaba.fastjson.JSONObject;
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
    Object getToken(){
        String submitToken = UUID.randomUUID().toString();
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(submitToken,submitToken,3600, TimeUnit.SECONDS);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("submitToken",submitToken);
        return jsonObject;
    }

    @RequestMapping("/submit")
    Object submit(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","success");
        return jsonObject;
    }



}
