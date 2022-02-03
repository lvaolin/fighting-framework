package com.dhy.demo.spring.JdbcTemplate.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title DynamicDataSource
 * @Description
 * @Author lvaolin
 * @Date 2022/2/3 21:40
 **/
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(){
        //创建分库数据源
        HashMap<Object, Object> dbMap = new HashMap<>();
        dbMap.put("dbkey1",new DruidDataSource());
        dbMap.put("dbkey2",new DruidDataSource());
        dbMap.put("dbkey3",new DruidDataSource());
        //设置动态数据源
        this.setDefaultTargetDataSource(dbMap.get("dbkey1"));
        this.setTargetDataSources(dbMap);
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return "dbkey1";
    }

}
