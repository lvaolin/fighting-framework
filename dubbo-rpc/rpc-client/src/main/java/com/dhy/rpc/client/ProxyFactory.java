package com.dhy.rpc.client;

import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    T getProxy(Class<T> interfaceClass){
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},new MyInvocationHandler());
    }
}
