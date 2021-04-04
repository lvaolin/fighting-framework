package com.dhy.demo01_mybatis.configuration;

import com.dhy.demo01_mybatis.annotation.MyMapperScan;
import com.dhy.demo01_mybatis.dto.User;
import com.dhy.demo01_mybatis.mapper.AccountMapper;
import com.dhy.demo01_mybatis.mapper.OrderMapper;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MyMapperScan("com.dhy.demo01_mybatis.mapper")
public class BeanConfig {

//    @Bean
//    public User user(){
//       return new User();
//    }
//    @Bean
//    public OrderMapper orderMapper() throws Exception {
//        MyMapperFactoryBean<OrderMapper> myMapperFactoryBean = new MyMapperFactoryBean<>(OrderMapper.class);
//        return myMapperFactoryBean.getObject();
//    }
//    @Bean
//    public AccountMapper accountMapper() throws Exception {
//        MyMapperFactoryBean<AccountMapper> myMapperFactoryBean = new MyMapperFactoryBean<>(AccountMapper.class);
//        return myMapperFactoryBean.getObject();
//    }
}
