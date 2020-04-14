package com.dhy.proxy.jdk;

/**
 * @author lvaolin
 * @create 2020/4/13 5:55 PM
 */
public class Person implements IPerson {
    @Override
    public String eat() {
        System.out.println("吃饭");
        return null;
    }
}
