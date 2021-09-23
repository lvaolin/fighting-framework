package com.dhy.demo.spring.JdbcTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

@Service

public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStorageDao seataStorageDao;
    @Autowired
    private TransactionTemplate transactionTemplate;
    /**
     * 只读事务
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<SeataStoragePo> selectAll() {
       return seataStorageDao.productSelectAll();
    }


    @Override
    @Transactional(readOnly = true)
    public int selectCount(){
        return seataStorageDao.productCount();
    }

    /**
     * 使用@Transactional控制事务，粒度大
     * @param po
     * @return
     */
    @Override
    @Transactional
    public int insert(SeataStoragePo po){
        seataStorageDao.productInsert(po);//依靠Transactional注解回滚
        seataStorageDao.productInsert(po);
        seataStorageDao.productInsert(po);
        return 1/0;
    }

    /**
     * 使用transactionTemplate 控制事务，粒度小
     * @param list
     */
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

    /**
     * 自动提交事务
     * @param po
     * @return
     */
    @Override
    public int delete(SeataStoragePo po) {
        return seataStorageDao.productDelete(po);
    }

    /**
     * 自动提交事务
     * @param po
     * @return
     */
    @Override
    public int update(SeataStoragePo po) {
        return seataStorageDao.productUpdate(po);
    }

}
