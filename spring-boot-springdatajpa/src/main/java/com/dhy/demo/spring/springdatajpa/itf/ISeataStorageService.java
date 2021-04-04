package com.dhy.demo.spring.springdatajpa.itf;

import com.dhy.demo.spring.springdatajpa.po.SeataStoragePo;

import java.util.List;

public interface ISeataStorageService {

     List<SeataStoragePo> selectAll();

     List<SeataStoragePo> findByStock(SeataStoragePo po);

     long selectCount();

     SeataStoragePo insert(SeataStoragePo po);

     void delete(SeataStoragePo po);

     SeataStoragePo update(SeataStoragePo po);

     SeataStoragePo updateByPrimaryKeySelective(SeataStoragePo po);
}
