package com.dhy.demo.spring.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
