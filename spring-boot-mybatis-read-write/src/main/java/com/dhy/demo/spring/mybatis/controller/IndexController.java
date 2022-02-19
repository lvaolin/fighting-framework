package com.dhy.demo.spring.mybatis.controller;

import com.dhy.demo.spring.mybatis.config.TraceUtil;
import com.dhy.demo.spring.mybatis.itf.ISeataStorageService;
import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import com.dhy.demo.spring.mybatis.utils.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
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
        TraceUtil.setDbKeyMaster("biz-ds1");
        return seataStorageService.selectAll();
    }

    @RequestMapping("/product/all-xml")
    Object getProductByXml(){
        return seataStorageService.selectAllByXml();
    }

    @RequestMapping("/product/insert")
    Object insertProduct(SeataStoragePo po){
        if(po==null||po.getStock()==null||po.getPrice()==null){
            throw new BusinessException("","参数不完整");
        }
        TraceUtil.setDbKeyMaster("biz-ds1");
        return seataStorageService.insert(po);
    }

    @RequestMapping("/product/delete")
    Object deleteProduct(SeataStoragePo po){
        if(po==null||po.getId()==null){
            throw new BusinessException("","参数不完整");
        }
        TraceUtil.setDbKeyMaster("biz-ds1");
        return seataStorageService.delete(po);
    }


    @RequestMapping("/product/update")
    Object updateProduct(SeataStoragePo po){
        if(po==null||po.getId()==null){
            throw new BusinessException("","参数不完整");
        }
        TraceUtil.setDbKeyMaster("biz-ds1");
        return seataStorageService.update(po);
    }

    @RequestMapping("/product/count")
    Object countProduct(){
        TraceUtil.setDbKeyMaster("biz-ds1");
        return seataStorageService.selectCount();
    }




    @Data
    class Dhy{
        private String name;
        private String desc;
    }

}
