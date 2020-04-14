package com.dhy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lvaolin
 * @create 2020/4/13 5:56 PM
 */
public class MyProxy implements InvocationHandler{

    Object origin;
    public MyProxy(Object origin){
        this.origin = origin;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(origin,args);
        System.out.println("after");
        return result;
    }
}
