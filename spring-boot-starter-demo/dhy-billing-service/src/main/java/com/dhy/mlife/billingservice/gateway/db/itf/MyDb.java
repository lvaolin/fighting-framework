package com.dhy.mlife.billingservice.gateway.db.itf;

import java.util.List;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/19 下午8:15
 */
public interface MyDb {
    int insert(SeataStoragePo po);

    int deleteByPrimaryKey(Long id);

    SeataStoragePo selectByPrimaryKey(Long id);

    List<SeataStoragePo> selectAll();
}
