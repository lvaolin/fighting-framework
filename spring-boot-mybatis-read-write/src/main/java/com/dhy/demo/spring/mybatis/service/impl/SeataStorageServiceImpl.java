package com.dhy.demo.spring.mybatis.service.impl;

import com.dhy.demo.spring.mybatis.config.DsReadonly;
import com.dhy.demo.spring.mybatis.config.TraceUtil;
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

    @DsReadonly
    @Override
    public List<SeataStoragePo> selectAll() {
        System.out.println("selectAll:"+TraceUtil.getReadonly()+TraceUtil.getDbKeyReadonly());
        return dbGateway.productSelectAll();
    }

    @DsReadonly
    @Override
    public List<SeataStoragePo> selectAllByXml() {
        System.out.println("selectAllByXml:"+TraceUtil.getDbKeyReadonly());
        return dbGateway.productSelectAllByXml();
    }


    @DsReadonly
    @Override
    public int selectCount(){
        System.out.println("selectCount:"+TraceUtil.getReadonly()+TraceUtil.getDbKeyReadonly());
        return dbGateway.productCount();
    }


    @Override
    public int insert(SeataStoragePo po){
        System.out.println("insert:"+TraceUtil.getReadonly());
        return dbGateway.productInsert(po);
    }

    @Override
    public int delete(SeataStoragePo po) {
        System.out.println("delete:"+TraceUtil.getReadonly());
        return dbGateway.productDelete(po);
    }

    @Override
    public int update(SeataStoragePo po) {
        System.out.println("update:"+TraceUtil.getReadonly());
        return dbGateway.productUpdate(po);
    }
}
