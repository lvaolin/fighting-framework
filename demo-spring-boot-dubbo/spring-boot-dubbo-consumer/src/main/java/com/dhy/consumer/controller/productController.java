package com.dhy.consumer.controller;

import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.SeataStoragePo;
import com.dhy.common.utils.BusinessException;
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
