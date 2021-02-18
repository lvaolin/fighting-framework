package com.dhy.consumer.service;

import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.OrderPo;
import com.dhy.common.itf.SeataStoragePo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.util.List;

@DubboService
@Component
public class OrderServiceImpl implements IOrderService {
    @DubboReference
    private ISeataStorageService seataStorageService;
    @Override
    public List<OrderPo> selectAll() {
        List<SeataStoragePo> seataStoragePos = seataStorageService.selectAll();
        return null;
    }


}
