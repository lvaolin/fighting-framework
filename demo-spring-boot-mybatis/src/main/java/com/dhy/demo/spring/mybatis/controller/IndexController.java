package com.dhy.demo.spring.mybatis.controller;

import com.dhy.demo.spring.mybatis.service.ISeataStorageService;
import com.dhy.demo.spring.mybatis.service.SeataStoragePo;
import com.dhy.demo.spring.mybatis.utils.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/jdbc")
public class IndexController {

    @Autowired
    private ISeataStorageService seataStorageService;


    @RequestMapping("/")
    Object index(){

        Dhy dhy = new Dhy();
        dhy.setName("lvaolin");
        dhy.setDesc("大黄鸭欢迎您");

        return dhy;
    }


    @RequestMapping("/product")
    Object getProduct(){
        return seataStorageService.selectAll();
    }

    @RequestMapping("/product/insert")
    Object insertProduct(SeataStoragePo po){
        if(po==null||po.getStock()==null||po.getPrice()==null){
            throw new BusinessException("","参数不完整");
        }
        return seataStorageService.insert(po);
    }

    @RequestMapping("/product/delete")
    Object deleteProduct(SeataStoragePo po){
        if(po==null||po.getId()==null){
            throw new BusinessException("","参数不完整");
        }
        return seataStorageService.delete(po);
    }


    @RequestMapping("/product/update")
    Object updateProduct(SeataStoragePo po){
        if(po==null||po.getId()==null){
            throw new BusinessException("","参数不完整");
        }
        return seataStorageService.update(po);
    }

    @RequestMapping("/product/count")
    Object countProduct(){
        return seataStorageService.selectCount();
    }




    @Data
    class Dhy{
        private String name;
        private String desc;
    }

}
