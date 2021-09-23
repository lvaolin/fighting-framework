package com.dhy.demo.spring.jdbc.controller;

import com.dhy.demo.spring.jdbc.service.ISeataStorageService;
import com.dhy.demo.spring.jdbc.service.SeataStoragePo;
import com.dhy.demo.spring.jdbc.utils.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    Object insertProduct(@RequestBody SeataStoragePo po){
        if(po==null||po.getStock()==null||po.getPrice()==null){
            throw new BusinessException("","参数不完整");
        }
        return seataStorageService.insert(po);
    }

    @RequestMapping("/product/batchInsert")
    Object insertProduct(@RequestBody List<SeataStoragePo> list){
        for (SeataStoragePo seataStoragePo : list) {
            if (seataStoragePo.getId()==null) {
                throw new BusinessException("","id不能为空");
            }
        }
        seataStorageService.batchInsert(list);
        return true;
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
