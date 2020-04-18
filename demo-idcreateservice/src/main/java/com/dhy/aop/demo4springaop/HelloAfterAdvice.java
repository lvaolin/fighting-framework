package com.dhy.aop.demo4springaop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 实现后置增强接口
 *
 * @author lvaolin
 * @create 2020/1/22 3:12 PM
 */
public class HelloAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("after hello");
    }
}
