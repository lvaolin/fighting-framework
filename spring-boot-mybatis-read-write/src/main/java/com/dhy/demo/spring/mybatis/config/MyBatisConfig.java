package com.dhy.demo.spring.mybatis.config;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.*;


/**
 * 动态数据源与mybatis集成
 */
@Configuration
public class MyBatisConfig {

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource()  {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dynamicDataSource.setDefaultTargetDataSource(getNewDataSourceInstance());

        dataSourceMap.put("biz-ds1",getNewDataSourceInstance());
        dataSourceMap.put("biz-ds1-readonly1",getNewDataSourceInstance());
        dataSourceMap.put("biz-ds1-readonly2",getNewDataSourceInstance());

        dataSourceMap.put("biz-ds2",getNewDataSourceInstance());
        dataSourceMap.put("biz-ds2-readonly1",getNewDataSourceInstance());
        dataSourceMap.put("biz-ds2-readonly2",getNewDataSourceInstance());

        dynamicDataSource.setTargetDataSources(dataSourceMap);

        dynamicDataSource.afterPropertiesSet();

        return dynamicDataSource;
    }

    @Bean("dsReadOnlyKeyMapping")
    public Map<String, String> dsReadOnlyKeyMapping(){
        //主库名称与只读库名称的映射
        HashMap<String, String> map = new HashMap<>();
        map.put("biz-ds1","biz-ds1-readonly1,biz-ds1-readonly2");
        map.put("biz-ds2","biz-ds2-readonly1,biz-ds2-readonly2");
        return map;
    }

    @Bean("lazyDataSource")
    public DataSource lazyDataSource() {
        LazyConnectionDataSourceProxy proxy = new LazyConnectionDataSourceProxy();
        proxy.setTargetDataSource(dynamicDataSource());
        proxy.afterPropertiesSet();
        return proxy;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(lazyDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.dhy.demo.spring.mybatis.infrastructure.datebase.mybatis.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(lazyDataSource());
        dataSourceTransactionManager.setNestedTransactionAllowed(false);
        return dataSourceTransactionManager;
    }

    private DruidDataSource getNewDataSourceInstance(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/seata_storage?characterEncoding=utf8&serverTimezone=UTC&useUnicode=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //分表过滤器
        druidDataSource.setProxyFilters(Collections.singletonList(new MyDruidSqlRewriteFilter()));
        return druidDataSource;
    }

}