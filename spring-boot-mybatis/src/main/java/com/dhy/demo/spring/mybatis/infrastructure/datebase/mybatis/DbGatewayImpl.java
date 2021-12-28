package com.dhy.demo.spring.mybatis.infrastructure.datebase.mybatis;

import com.dhy.demo.spring.mybatis.infrastructure.datebase.mybatis.mapper.SeataStorageMapper;
import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import com.dhy.demo.spring.mybatis.service.gateway.IDbGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project spring-boot-mybatis
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/12/28 上午9:28
 */
@Component
public class DbGatewayImpl implements IDbGateway {
    @Autowired
    private SeataStorageMapper seataStorageMapper;

    @Override
    public List<SeataStoragePo> productSelectAll() {
        return seataStorageMapper.productSelectAll();
    }

    @Override
    public int productCount() {
        return seataStorageMapper.productCount();
    }

    @Override
    public int productInsert(SeataStoragePo po) {
        return seataStorageMapper.productInsert(po);
    }

    @Override
    public int productDelete(SeataStoragePo po) {
        return seataStorageMapper.productDelete(po);
    }

    @Override
    public int productUpdate(SeataStoragePo po) {
        return seataStorageMapper.productUpdate(po);
    }

    @Override
    public List<SeataStoragePo> productSelectAllByXml() {
        return seataStorageMapper.productSelectAllByXml();
    }
}
