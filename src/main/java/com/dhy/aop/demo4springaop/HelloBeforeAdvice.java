package com.dhy.aop.demo4springaop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 实现前置增强接口
 *
 * @author lvaolin
 * @create 2020/1/22 3:09 PM
 */
public class HelloBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("before hello");
    }
}
