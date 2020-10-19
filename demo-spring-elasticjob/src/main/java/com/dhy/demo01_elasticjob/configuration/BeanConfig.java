package com.dhy.demo01_elasticjob.configuration;

import com.dhy.demo01_elasticjob.dto.User;
import com.dhy.demo01_elasticjob.mapper.AccountMapper;
import com.dhy.demo01_elasticjob.mapper.OrderMapper;
import com.dhy.demo01_elasticjob.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserMapper userMapper() throws Exception {
        MyMapperFactoryBean<UserMapper> myMapperFactoryBean = new MyMapperFactoryBean<>(UserMapper.class);
        return myMapperFactoryBean.getObject();
    }
    @Bean
    public OrderMapper orderMapper() throws Exception {
        MyMapperFactoryBean<OrderMapper> myMapperFactoryBean = new MyMapperFactoryBean<>(OrderMapper.class);
        return myMapperFactoryBean.getObject();
    }
    @Bean
    public AccountMapper accountMapper() throws Exception {
        MyMapperFactoryBean<AccountMapper> myMapperFactoryBean = new MyMapperFactoryBean<>(AccountMapper.class);
        return myMapperFactoryBean.getObject();
    }
}
