package com.dhy.demo01_mybatis.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface MyMapperScan {
    String value();
}
