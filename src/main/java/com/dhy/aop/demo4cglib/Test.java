package com.dhy.aop.demo4cglib;

/**
 * 测试Cglib实现代理
 *
 * @author lvaolin
 * @create 2020/1/6 4:15 PM
 */
public class Test {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        IHello hello = (Hello)cglibProxy.createProxy(Hello.class);
        hello.sayHello();
        World world = (World)cglibProxy.createProxy(World.class);
        world.sayWorld();
    }
}
