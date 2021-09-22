package com.dhy.demo.spring.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStorageDao seataStorageDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

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
    public void batchInsert(List<SeataStoragePo> list) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                for (SeataStoragePo seataStoragePo : list) {
                    seataStorageDao.productInsert(seataStoragePo);
                }
                return null;
            }
        });

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
