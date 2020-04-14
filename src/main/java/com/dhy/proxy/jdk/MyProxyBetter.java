package com.dhy.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lvaolin
 * @create 2020/4/13 5:56 PM
 */
public class MyProxyBetter implements InvocationHandler{

    Object origin;
    public MyProxyBetter(Object origin){
        this.origin = origin;
    }
    public static Object getInstance(Object origin){
        return Proxy.newProxyInstance(origin.getClass().getClassLoader(),origin.getClass().getInterfaces(),new MyProxyBetter(origin));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(origin,args);
        System.out.println("after");
        return result;
    }
}
