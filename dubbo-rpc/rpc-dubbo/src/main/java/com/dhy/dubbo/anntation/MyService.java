package com.dhy.dubbo.anntation;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title MyService
 * @Description  用来表明此实现类 提供RPC远程服务
 * @Author lvaolin
 * @Date 2021/3/13 20:38
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
    String name() default "";
}
