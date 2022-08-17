package com.dhy.demo.spring.controller;

import com.dhy.demo.spring.service.prototype.BillInfoService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spring")
public class PrototypeControllerYes {

    /**
     * BillInfoService 为多例类型 prototype
     * 正确的使用方式之一  ObjectFactory
     */
    @Autowired
    private ObjectFactory<BillInfoService> billInfoServiceObjectFactory;

    @RequestMapping("/prototype2/set")
    Object set1(HttpServletRequest request )  {
        BillInfoService billInfoService = billInfoServiceObjectFactory.getObject();
        String flag = request.getParameter("flag");
        billInfoService.setFlag(flag);
        billInfoService.showInfo();
        return billInfoService.getFlag();
    }

    @RequestMapping("/prototype2/get")
    Object get1(HttpServletRequest request )  {
        BillInfoService billInfoService = billInfoServiceObjectFactory.getObject();
        billInfoService.showInfo();
        return billInfoService.getFlag();
    }


}
