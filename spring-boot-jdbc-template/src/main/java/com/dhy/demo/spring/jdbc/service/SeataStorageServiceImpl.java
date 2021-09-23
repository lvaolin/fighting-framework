package com.dhy.demo.spring.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@Transactional
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStorageDao seataStorageDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<SeataStoragePo> selectAll() {
       return seataStorageDao.productSelectAll();
    }


    @Override
    public int selectCount(){
        return seataStorageDao.productCount();
    }


    @Override
    public int insert(SeataStoragePo po){
        seataStorageDao.productInsert(po);//依靠Transactional注解回滚
        return 1/0;
    }

    @Override
    public void batchInsert(List<SeataStoragePo> list) {
        Boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                for (SeataStoragePo seataStoragePo : list) {
                    seataStorageDao.productInsert(seataStoragePo);
                    seataStorageDao.productInsert(seataStoragePo);//主键重复异常，依靠transactionTemplate回滚
                }
                return true;
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
