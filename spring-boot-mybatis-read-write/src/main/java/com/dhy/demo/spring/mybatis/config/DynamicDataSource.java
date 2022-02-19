package com.dhy.demo.spring.mybatis.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Title 动态数据源
 * @Description
 * @Author lvaolin
 * @Date 2022/2/3 21:40
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TraceUtil.getReadonly()?TraceUtil.getDbKeyReadonly():TraceUtil.getDbKeyMaster();
    }

}
