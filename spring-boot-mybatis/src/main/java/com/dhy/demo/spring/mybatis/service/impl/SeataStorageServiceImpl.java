package com.dhy.demo.spring.mybatis.service.impl;

import com.dhy.demo.spring.mybatis.service.mapper.SeataStorageMapper;
import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import com.dhy.demo.spring.mybatis.itf.ISeataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStorageMapper seataStorageMapper;

    @Override
    public List<SeataStoragePo> selectAll() {
       return seataStorageMapper.productSelectAll();
    }

    @Override
    public List<SeataStoragePo> selectAllByXml() {
        return seataStorageMapper.productSelectAllByXml();
    }


    @Override
    public int selectCount(){
        return seataStorageMapper.productCount();
    }


    @Override
    public int insert(SeataStoragePo po){
        return seataStorageMapper.productInsert(po);
    }

    @Override
    public int delete(SeataStoragePo po) {
        return seataStorageMapper.productDelete(po);
    }

    @Override
    public int update(SeataStoragePo po) {
        return seataStorageMapper.productUpdate(po);
    }
}
