package com.dhy.demo.spring.JdbcTemplate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import java.util.HashMap;

/**
 * @Title ShardingDB
 * @Description
 * @Author lvaolin
 * @Date 2022/2/3 21:27
 **/
@Configuration
public class ShardingDB implements BeanPostProcessor {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof DruidDataSource) {
              //记录账号密码
            DruidDataSource druidDataSource = (DruidDataSource) bean;
            String properties = druidDataSource.getProperties();
            //查询获取分库信息
        }

        if (bean instanceof DynamicDataSource) {
            System.out.println("------------");
        }

        return bean;
    }


    @Bean
    public LazyConnectionDataSourceProxy lazyDataSource(){
        LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy = new LazyConnectionDataSourceProxy();
        lazyConnectionDataSourceProxy.setTargetDataSource(dynamicDataSource);
        return lazyConnectionDataSourceProxy;
    }
}
