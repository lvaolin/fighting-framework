package com.dhy.demo.spring.controller;

import com.dhy.demo.spring.service.ISeataStorageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 批量操作API控制器
 */
@Controller
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private ISeataStorageService seataStorageService;

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
