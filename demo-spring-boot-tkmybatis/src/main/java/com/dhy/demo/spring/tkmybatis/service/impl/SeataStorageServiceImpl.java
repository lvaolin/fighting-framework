package com.dhy.demo.spring.tkmybatis.service.impl;

import com.dhy.demo.spring.tkmybatis.service.mapper.SeataStorageMapper;
import com.dhy.demo.spring.tkmybatis.po.SeataStoragePo;
import com.dhy.demo.spring.tkmybatis.itf.ISeataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Autowired
    private SeataStorageMapper seataStorageMapper;

    @Override
    public List<SeataStoragePo> selectAll() {
       return seataStorageMapper.selectAll();
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
        //这个方法是全量更新，注意---po中为null的会将数据库对应列清空
        return seataStorageMapper.updateByPrimaryKey(po);
    }

    @Override
    public int updateByPrimaryKeySelective(SeataStoragePo po) {
        //这个方法是增量更新，只更新po中不为null的列
        return seataStorageMapper.updateByPrimaryKeySelective(po);
    }
}
