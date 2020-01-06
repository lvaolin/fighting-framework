package com.aop.demo4jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lvaolin
 * @create 2019/7/15 6:33 PM
 */
public class MyInvocationHandle implements InvocationHandler{

    private Object delegate;

    public Object bind(Object delegate){
        this.delegate = delegate;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),this.delegate.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("before  ");
            result = method.invoke(this.delegate,args);
            System.out.println("after  ");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
