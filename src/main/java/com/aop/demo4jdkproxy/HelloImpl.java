package com.aop.demo4jdkproxy;

/**
 * @author lvaolin
 * @create 2019/7/15 6:32 PM
 */
public class HelloImpl implements IHello{
    @Override
    public void sayHello(String name) {
        System.out.println("hello......."+name);
    }


}
