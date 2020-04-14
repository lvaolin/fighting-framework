package com.dhy.proxy.jdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author lvaolin
 * @create 2020/4/13 5:56 PM
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //-----Spring 工厂模式生成对象-----
        IPerson person = (IPerson) Factory.getInstance("person");
        person.eat();
        IHello hello = (IHello)  Factory.getInstance("hello");
        hello.say();
        System.out.println("----------------");
        //-----jdk动态代理模式生成对象-----
        IHello helloJdk = (IHello)  Proxy.newProxyInstance(Hello.class.getClassLoader(),Hello.class.getInterfaces(),new MyProxy(new Hello()));
        helloJdk.say();
        System.out.println("----------------");
        //-----类反射生成对象---------
        IHello hello2 = Hello.class.newInstance();
        hello2.say();
        System.out.println("----------------");
        //------传统方式生成对象--------
        IHello hello3 = new Hello();
        hello3.say();
        System.out.println("----------------");
        //-----------改进生成代理，更简单一些---
        IPerson person1 = (IPerson)MyProxyBetter.getInstance(new Person());
        person1.eat();
    }
}
