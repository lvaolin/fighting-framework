package com.dhy.demo.spring.springdatajpa.service.impl;

import com.dhy.demo.spring.springdatajpa.dao.SeataStoreageRepository;
import com.dhy.demo.spring.springdatajpa.po.SeataStoragePo;
import com.dhy.demo.spring.springdatajpa.itf.ISeataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStoreageRepository seataStoreageRepository;

    @Override
    public List<SeataStoragePo> selectAll() {
       return seataStoreageRepository.findAll();
    }

    @Override
    public List<SeataStoragePo> findByStock(SeataStoragePo po) {
        return seataStoreageRepository.findByStock(po.getStock());
    }


    @Override
    public long selectCount(){
        return seataStoreageRepository.count();
    }


    @Override
    public SeataStoragePo insert(SeataStoragePo po){
        return seataStoreageRepository.save(po);
    }

    @Override
    public void delete(SeataStoragePo po) {
         seataStoreageRepository.delete(po);
    }

    @Override
    public SeataStoragePo update(SeataStoragePo po) {
        //这个方法是全量更新，注意---po中为null的会将数据库对应列清空
        return seataStoreageRepository.save(po);
    }

    @Override
    public SeataStoragePo updateByPrimaryKeySelective(SeataStoragePo po) {
        //这个方法是增量更新，只更新po中不为null的列
        //jpa 中没有类似方法---
        return seataStoreageRepository.save(po);
    }
}
