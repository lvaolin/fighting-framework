package com.dhy.dubbo.anntation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title MyReference
 * @Description  用来表明此依赖为远程接口，需要生成代理
 * @Author lvaolin
 * @Date 2021/3/13 20:38
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyReference {
    String name() default "";
}
