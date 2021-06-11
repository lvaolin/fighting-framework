package com.dhy.demo.spring.redis.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/redis")
public class IndexController {

    @RequestMapping("/index")
    Object index(HttpServletRequest request ){
        String name = request.getParameter("name");
        Dhy dhy = new Dhy();
        dhy.setName(name);
        dhy.setDesc("大黄鸭欢迎您");
        return dhy;
    }

    @Data
    class Dhy{
        private String name;
        private String desc;
    }

}
