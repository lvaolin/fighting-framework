package com.dhy.spring.controller;

import com.dhy.spring.service.ISeataStorageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spring")
public class IndexController {

    @Autowired
    private ISeataStorageService seataStorageService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/1")
    Object index(){

        System.out.println(request);
        String name = request.getParameter("name");
        Dhy dhy = new Dhy();
        dhy.setName(name);
        dhy.setDesc("大黄鸭欢迎您");

        return dhy;
    }

    @RequestMapping("/2")
    Object index2(HttpServletRequest request){
        System.out.println(request);
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
