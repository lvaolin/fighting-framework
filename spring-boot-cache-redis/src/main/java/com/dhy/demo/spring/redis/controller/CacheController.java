package com.dhy.demo.spring.redis.controller;

import com.dhy.demo.spring.redis.service.ICacheService;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private ICacheService cacheService;

    @RequestMapping("/setCache1")
    public Object setCache1(@RequestParam("name") String name){
        return cacheService.setCache1(name);
    }

    @RequestMapping("/setCache2")
    public Object setCache2(@RequestParam("name") String name){
        return cacheService.setCache2(name);
    }

    @RequestMapping("/updateCache1")
    public Object updateCache1(@RequestParam("name") String name){
        return cacheService.updateCache1(name);
    }

    @RequestMapping("/deleteCache1")
    public Object deleteCache1(@RequestParam("name") String name){
        return cacheService.deleteCache1(name);
    }


}
