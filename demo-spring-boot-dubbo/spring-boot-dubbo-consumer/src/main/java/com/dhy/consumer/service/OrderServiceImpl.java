package com.dhy.consumer.service;

import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.OrderPo;
import com.dhy.common.itf.SeataStoragePo;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class OrderServiceImpl implements IOrderService {
    @Reference
    private ISeataStorageService seataStorageService;
    @Override
    public List<OrderPo> selectAll() {
        List<SeataStoragePo> seataStoragePos = seataStorageService.selectAll();
        return null;
    }


}
