package com.dhy.demo.spring.mybatis.itf;

import com.dhy.demo.spring.mybatis.po.SeataStoragePo;

import java.util.List;

public interface ISeataStorageService {

     List<SeataStoragePo> selectAll();

     List<SeataStoragePo> selectAllByXml();

     int selectCount();

     int insert(SeataStoragePo po);

     int delete(SeataStoragePo po);

     int update(SeataStoragePo po);
}
