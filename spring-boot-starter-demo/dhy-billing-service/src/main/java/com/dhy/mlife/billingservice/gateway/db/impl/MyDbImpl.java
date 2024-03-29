package com.dhy.mlife.billingservice.gateway.db.impl;

import com.dhy.mlife.billingservice.gateway.db.impl.mapper.SeataStorageMapper;
import com.dhy.mlife.billingservice.gateway.db.itf.MyDb;
import com.dhy.mlife.billingservice.gateway.db.itf.SeataStoragePo;
import com.dhy.mlife.cache.impl.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/19 下午8:18
 */
@Component
public class MyDbImpl implements MyDb {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private SeataStorageMapper seataStorageMapper;

    @Override
    public int insert(SeataStoragePo po) {
        return seataStorageMapper.insert(po);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return seataStorageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SeataStoragePo selectByPrimaryKey(Long id) {
        return seataStorageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SeataStoragePo> selectAll() {
        return seataStorageMapper.productSelectAllByXml();
    }


}
