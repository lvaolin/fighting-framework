package com.dhy.temp.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {

    @Test
    @DisplayName("纯接口代理")
    public  void proxyForInterface() {

        Class<?>[] interfaces = {IMathService.class};

        IMathService mathService = (IMathService) Proxy.newProxyInstance(IMathService.class.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("add")) {
                    return (int)args[0] + (int)args[1];
                }
                return null;
            }
        });

        int x=1;
        int y=1;

        System.out.println(mathService.add(x, y));


    }

    @Test
    @DisplayName("实现类代理")
    public  void proxyForClass() {

        IMathService mathService = (IMathService) Proxy.newProxyInstance(MathServiceImpl.class.getClassLoader(), MathServiceImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("add before");
                Object o =  method.invoke(proxy,args);
                System.out.println("add after");
                return o;
            }
        });

        int x=1;
        int y=1;

        System.out.println(mathService.add(x, y));


    }
}
