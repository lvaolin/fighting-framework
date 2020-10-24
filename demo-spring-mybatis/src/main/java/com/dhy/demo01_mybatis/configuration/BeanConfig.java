package com.dhy.demo01_mybatis.configuration;

import com.dhy.demo01_mybatis.dto.User;
import com.dhy.demo01_mybatis.mapper.AccountMapper;
import com.dhy.demo01_mybatis.mapper.OrderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public User user(){
       return new User();
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
