package com.dhy.demo.spring.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title DynamicDataSource
 * @Description
 * @Author lvaolin
 * @Date 2022/2/3 21:40
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TraceUtil.getDbKey();
    }

}
