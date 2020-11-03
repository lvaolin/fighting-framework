package com.dhy.common.itf;

import java.util.List;

public interface ISeataStorageService {

     List<SeataStoragePo> selectAll();

     int selectCount();

     int insert(SeataStoragePo po);

     int delete(SeataStoragePo po);

     int update(SeataStoragePo po);
}
