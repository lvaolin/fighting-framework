package com.dhy.proxy.jdk;

/**
 * @author lvaolin
 * @create 2020/4/14 9:30 AM
 */
public class Hello implements IHello{
    @Override
    public void say() {
        System.out.println("say hello");
    }
}
