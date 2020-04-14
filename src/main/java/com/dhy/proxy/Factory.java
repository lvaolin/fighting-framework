package com.dhy.proxy;

/**
 * @author lvaolin
 * @create 2020/4/14 9:33 AM
 */
public class Factory {
    public  static  Object  getInstance(String key){
        switch (key){
            case "person":
                return java.lang.reflect.Proxy.newProxyInstance(Person.class.getClassLoader(),Person.class.getInterfaces(),new MyProxy(new Person()));
            case "hello":
                return java.lang.reflect.Proxy.newProxyInstance(Hello.class.getClassLoader(),Hello.class.getInterfaces(),new MyProxy(new Hello()));
            default:
                return null;
        }
    }
}
