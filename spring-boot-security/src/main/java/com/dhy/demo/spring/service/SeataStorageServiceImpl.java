package com.dhy.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Primary
@Service("seataStorageService1")
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStorageDao seataStorageDao;

    @Override
    public List<SeataStoragePo> selectAll() {
       return seataStorageDao.productSelectAll();
    }


    @Override
    public int selectCount(){
        return seataStorageDao.productCount();
    }


    @Override
    public int insert(SeataStoragePo po){
        return seataStorageDao.productInsert(po);
    }

    @Override
    public int delete(SeataStoragePo po) {
        return seataStorageDao.productDelete(po);
    }

    @Override
    public int update(SeataStoragePo po) {
        return seataStorageDao.productUpdate(po);
    }
}
