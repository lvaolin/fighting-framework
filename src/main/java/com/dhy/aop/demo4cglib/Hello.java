package com.dhy.aop.demo4cglib;

/**
 * 代理目标类
 *
 * @author lvaolin
 * @create 2020/1/6 4:11 PM
 */
public class Hello implements IHello{

    public void sayHello(){
        System.out.println("hello cglib proxy");
    }
}
