package com.dhy.ratelimit.resilience4j.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/hello")

    Object hello(@RequestParam String name){
        System.out.println(name);
        return "hello"+name;
    }
}
