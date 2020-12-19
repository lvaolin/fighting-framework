package com.dhy.consumer.controller;

import com.dhy.common.itf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/selectAll")
    Object getProduct() {
        return orderService.selectAll();
    }



}
