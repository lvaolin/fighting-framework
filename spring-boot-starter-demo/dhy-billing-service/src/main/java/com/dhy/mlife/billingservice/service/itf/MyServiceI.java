package com.dhy.mlife.billingservice.service.itf;

import com.dhy.mlife.billingservice.gateway.db.itf.SeataStoragePo;

import java.util.List;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/11 上午9:57
 */
public interface MyServiceI {
    void bizMethod();

    SeataStoragePo selectByPrimaryKey(Long id);

    void setCache(String key, String value);

    List<SeataStoragePo> selectAll();
}
