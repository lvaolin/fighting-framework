package com.dhy.dubbo.framework;

import java.lang.reflect.Proxy;

/**
 * 代理工厂，生成代理对象
 * @param <T>
 */
public class ProxyFactory<T> {

    public  T getProxy(Class<T> interfaceClass){
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},new MyInvocationHandler());
    }
}
