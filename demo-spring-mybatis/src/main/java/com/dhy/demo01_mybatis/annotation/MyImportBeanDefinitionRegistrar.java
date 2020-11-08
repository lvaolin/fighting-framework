package com.dhy.demo01_mybatis.annotation;

import com.dhy.demo01_mybatis.configuration.MyMapperFactoryBean;
import com.dhy.demo01_mybatis.mapper.AccountMapper;
import com.dhy.demo01_mybatis.mapper.OrderMapper;
import com.dhy.demo01_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {

        registerBeanDefinitions(importingClassMetadata, registry);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //实际情况为：扫描包路径，把所有的mapper 的 bean definition信息注册进去
        BeanDefinitionBuilder userMapperBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyMapperFactoryBean.class);
        userMapperBuilder.addPropertyValue("mapperInterface", UserMapper.class);
        registry.registerBeanDefinition("userMapper", userMapperBuilder.getBeanDefinition());

        BeanDefinitionBuilder accountMapperBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyMapperFactoryBean.class);
        accountMapperBuilder.addPropertyValue("mapperInterface", AccountMapper.class);
        registry.registerBeanDefinition("accountMapper", accountMapperBuilder.getBeanDefinition());

        BeanDefinitionBuilder orderMapperBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyMapperFactoryBean.class);
        orderMapperBuilder.addPropertyValue("mapperInterface", OrderMapper.class);
        registry.registerBeanDefinition("orderMapper", orderMapperBuilder.getBeanDefinition());

        System.out.println("注册了所有的mapper的实现类信息 MyMapperFactoryBean");
    }
}
