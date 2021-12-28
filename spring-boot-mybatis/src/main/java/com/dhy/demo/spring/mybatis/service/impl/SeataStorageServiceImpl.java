package com.dhy.demo.spring.mybatis.service.impl;

import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import com.dhy.demo.spring.mybatis.itf.ISeataStorageService;
import com.dhy.demo.spring.mybatis.service.gateway.IDbGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private IDbGateway dbGateway;

    @Override
    public List<SeataStoragePo> selectAll() {
       return dbGateway.productSelectAll();
    }

    @Override
    public List<SeataStoragePo> selectAllByXml() {
        return dbGateway.productSelectAllByXml();
    }


    @Override
    public int selectCount(){
        return dbGateway.productCount();
    }


    @Override
    public int insert(SeataStoragePo po){
        return dbGateway.productInsert(po);
    }

    @Override
    public int delete(SeataStoragePo po) {
        return dbGateway.productDelete(po);
    }

    @Override
    public int update(SeataStoragePo po) {
        return dbGateway.productUpdate(po);
    }
}
