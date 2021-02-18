package com.dhy.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

public class MyImportSelector implements ImportSelector, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("-----MyImportSelector ----selectImports------");
        importingClassMetadata.getAnnotationTypes().forEach(System.out::println);
        System.out.println(beanFactory);
        return new String[]{MyConfig.class.getName(),MyConfig2.class.getName(),MyDto.class.getName()};
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
