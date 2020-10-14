package com.dhy.demo01.configuration;

import com.dhy.demo01.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new UserMapper();
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
