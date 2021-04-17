package com.dhy.demo.spring.controller;

import com.dhy.demo.spring.service.ISeataStorageService;
import com.dhy.demo.spring.service.SeataStoragePo;
import com.dhy.demo.spring.utils.BusinessException;
import lombok.Data;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/spring")
public class IndexController {

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
