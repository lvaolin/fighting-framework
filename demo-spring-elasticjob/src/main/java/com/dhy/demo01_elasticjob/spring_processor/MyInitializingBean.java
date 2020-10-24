package com.dhy.demo01_elasticjob.spring_processor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("属性填充完之后，init-method 之前");
    }
}
