package com.dhy.demo01_mybatis.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("获取userMapper的定义信息，篡改他的实现类");
        BeanDefinition userMapperBD = beanFactory.getBeanDefinition("userMapper");
        System.out.println("篡改之前："+userMapperBD.getBeanClassName());
        userMapperBD.getPropertyValues().removePropertyValue("mapperInterface");
        System.out.println("去除了mapperInterface依赖");
        userMapperBD.setBeanClassName("com.dhy.demo01_mybatis.mapper.UserMapperImpl");
        System.out.println("篡改之后："+userMapperBD.getBeanClassName());
    }

}
