package com.dhy.demo.spring.mybatis.service.impl;

import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import com.dhy.demo.spring.mybatis.itf.ISeataStorageService;
import com.dhy.demo.spring.mybatis.service.gateway.IDbGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Resource(name = "dbGatewayImpl")
    private IDbGateway dbGateway;
    @Resource(name = "myDbGateway")
    private IDbGateway myDbGateway;
    @Resource(name = "myDbGateway2")
    private IDbGateway myDbGateway2;
JdbcTemplate
    @Override
    public List<SeataStoragePo> selectAll(String type) {
        switch (type){
            case "1":
                return dbGateway.productSelectAll();
            case "2":
                return myDbGateway.productSelectAll();
            case "3":
                return myDbGateway2.productSelectAll();
        }
        return new ArrayList<>();
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
