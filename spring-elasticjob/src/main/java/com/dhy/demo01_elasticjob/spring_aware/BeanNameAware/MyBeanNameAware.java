package com.dhy.demo01_elasticjob.spring_aware.BeanNameAware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println("MyBeanNameAware有毛用？"+name);
    }
}
