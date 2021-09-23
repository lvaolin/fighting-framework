package com.dhy.demo.spring.JdbcTemplate.service;

import java.util.List;

public interface ISeataStorageService {

     List<SeataStoragePo> selectAll();

     int selectCount();

     int insert(SeataStoragePo po);

     void batchInsert(List<SeataStoragePo> list);

     int delete(SeataStoragePo po);

     int update(SeataStoragePo po);
}
