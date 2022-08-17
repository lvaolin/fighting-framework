package com.dhy.demo.spring.controller;

import com.dhy.demo.spring.service.prototype.BillInfoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spring")
public class PrototypeControllerNo {

    /**
     * BillInfoService 为多例类型 prototype
     * 不能直接用 Autowired 注解了
     */
    @Autowired
    private BillInfoService billInfoService;

    @RequestMapping("/prototype1/set")
    Object set1(HttpServletRequest request )  {
        String flag = request.getParameter("flag");
        billInfoService.setFlag(flag);
        billInfoService.showInfo();
        return billInfoService.getFlag();
    }

    @RequestMapping("/prototype1/get")
    Object get1(HttpServletRequest request )  {
        billInfoService.showInfo();
        return billInfoService.getFlag();
    }

    @Data
    class Dhy{
        private String name;
        private String desc;
        private long time;
    }

}
