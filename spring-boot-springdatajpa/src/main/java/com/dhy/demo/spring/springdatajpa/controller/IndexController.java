package com.dhy.demo.spring.springdatajpa.controller;

import com.dhy.demo.spring.springdatajpa.itf.ISeataStorageService;
import com.dhy.demo.spring.springdatajpa.po.SeataStoragePo;
import com.dhy.demo.spring.springdatajpa.utils.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/springdatajpa")
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


    @RequestMapping("/product/all")
    Object getProduct(){
        return seataStorageService.selectAll();
    }

    @RequestMapping("/product/findByStock")
    Object findByStock(SeataStoragePo po) {
        return seataStorageService.findByStock(po);
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
        seataStorageService.delete(po);
        return true;
    }


    @RequestMapping("/product/update")
    Object updateProduct(SeataStoragePo po){
        if(po==null||po.getId()==null){
            throw new BusinessException("","参数不完整");
        }
        return seataStorageService.update(po);
    }

    @RequestMapping("/product/update-selective")
    Object updateProductSelective(SeataStoragePo po){
        if(po==null||po.getId()==null){
            throw new BusinessException("","参数不完整");
        }
        return seataStorageService.updateByPrimaryKeySelective(po);
    }

    @RequestMapping("/product/count")
    Object countProduct(){
        //查询数量，自带方法
        return seataStorageService.selectCount();
    }




    @Data
    class Dhy{
        private String name;
        private String desc;
    }

}
