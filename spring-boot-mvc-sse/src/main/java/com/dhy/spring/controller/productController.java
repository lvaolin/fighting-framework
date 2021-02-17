package com.dhy.spring.controller;

import com.dhy.spring.service.ISeataStorageService;
import com.dhy.spring.service.SeataStoragePo;
import com.dhy.spring.utils.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/product")
public class productController {

   // @Autowired
    //@Qualifier("seataStorageService3")
    @Resource(name="seataStorageService2")
    private ISeataStorageService seataStorageService;

    @RequestMapping("/selectAll")
    Object getProduct() {
        return seataStorageService.selectAll();
    }

    @RequestMapping("/insert")
    Object insertProduct(SeataStoragePo po) {
        if (po == null || po.getStock() == null || po.getPrice() == null) {
            throw new BusinessException("", "参数不完整");
        }
        return seataStorageService.insert(po);
    }

    @RequestMapping("/delete")
    Object deleteProduct(SeataStoragePo po) {
        if (po == null || po.getId() == null) {
            throw new BusinessException("", "参数不完整");
        }
        return seataStorageService.delete(po);
    }


    @RequestMapping("/update")
    Object updateProduct(SeataStoragePo po) {
        if (po == null || po.getId() == null) {
            throw new BusinessException("", "参数不完整");
        }
        return seataStorageService.update(po);
    }

    @RequestMapping("/count")
    Object countProduct() {
        return seataStorageService.selectCount();
    }


}
