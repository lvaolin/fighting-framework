package com.dhy.demo.spring.mybatis.config;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @Title 只读数据源注解
 * @Description
 * @Author lvaolin
 * @Date 2022/2/19 18:19
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DsReadonly {
    String value() default "";
}
