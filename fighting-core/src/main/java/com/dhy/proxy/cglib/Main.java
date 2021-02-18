package com.dhy.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author lvaolin
 * @create 2020/4/14 10:54 AM
 */
public class Main {
    public static void main(String[] args) {
        //原始方式------
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new MyCglibMethodInterceptor());
        Person person = (Person)enhancer.create();
        person.eat();
        System.out.println("-------------");
        //改进方式
        Person person1 = (Person) CglibFactory.getInstance(Person.class,new MyCglibMethodInterceptor());
        person1.eat();
    }
}
