package com.dhy.ratelimit.resilience4j.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.dhy.ratelimit.resilience4j.common.IHelloService;
import feign.Param;
import feign.QueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class HelloController  {

    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        System.out.println(name);
        return "hello"+name;
    }
    @RequestMapping("/asyncHello")
    public String asyncHello(@RequestParam String name) {
        System.out.println(name);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "async hello"+name;
    }
}
