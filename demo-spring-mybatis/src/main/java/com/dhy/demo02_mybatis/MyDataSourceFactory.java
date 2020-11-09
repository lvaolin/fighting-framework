package com.dhy.demo02_mybatis;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyDataSourceFactory implements DataSourceFactory {
    private Properties properties;
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        StatFilter statFilter = new StatFilter();
        //慢sql打印
        statFilter.setLogSlowSql(true);
        statFilter.setConnectionStackTraceEnable(true);
        //慢sql 阀值
        statFilter.setSlowSqlMillis(1L);
        //合并类似sql
        statFilter.setMergeSql(true);
        List<Filter> filterList = new ArrayList<>();
        filterList.add(statFilter);
//--------------------------------------------------------
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(this.properties.getProperty("url"));
        dds.setUsername(this.properties.getProperty("username"));
        dds.setPassword(this.properties.getProperty("password"));
        dds.setProxyFilters(filterList);
        //监控记录定时输出到日志中
        dds.setTimeBetweenLogStatsMillis(20000);

        try {
            dds.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dds;
    }
}
