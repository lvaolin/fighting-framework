package com.dhy.demo.spring.controller;

import com.dhy.demo.spring.service.prototype.BillInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spring")
public class PrototypeControllerYes2 {

    /**
     * BillInfoService 为多例类型 prototype
     * 正确的使用方式之一  ac.getBean(BillInfoService.class)
     */
    @Autowired
    private ApplicationContext ac;

    @RequestMapping("/prototype3/set")
    Object set3(HttpServletRequest request )  {
        BillInfoService billInfoService = ac.getBean(BillInfoService.class);
        String flag = request.getParameter("flag");
        billInfoService.setFlag(flag);
        billInfoService.showInfo();
        return billInfoService.getFlag();
    }

    @RequestMapping("/prototype3/get")
    Object get3(HttpServletRequest request )  {
        BillInfoService billInfoService = ac.getBean(BillInfoService.class);
        billInfoService.showInfo();
        return billInfoService.getFlag();
    }


}
