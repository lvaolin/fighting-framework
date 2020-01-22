package com.aop.demo4springaop;

/**
 * @author lvaolin
 * @create 2020/1/22 3:18 PM
 */
public class HelloImpl implements IHello{
    @Override
    public void say(String name) {
        System.out.println(name+"  hello.....");
    }
}
