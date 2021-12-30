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
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/spring")
public class IndexController {

    @Autowired
    private ISeataStorageService seataStorageService;

    @RequestMapping("/index")
    Object index(HttpServletRequest request ) throws InterruptedException {
        TimeUnit.SECONDS.sleep(30);
        String name = request.getParameter("name");
        Dhy dhy = new Dhy();
        dhy.setName(name);
        dhy.setDesc("大黄鸭欢迎您");
        dhy.setTime(System.currentTimeMillis());
        return dhy;
    }

    @Data
    class Dhy{
        private String name;
        private String desc;
        private long time;
    }

}
