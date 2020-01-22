package com.dhy.aop.demo4jdkproxy;

/**
 * JDK动态代理测试
 * @author lvaolin
 * @create 2019/7/15 6:47 PM
 */
public class AopTest {
    public static void main(String[] args) {
        IHello hello =(IHello) new MyInvocationHandle().bind(new HelloImpl());
        hello.sayHello("马克");
        IWorld world = (IWorld) new MyInvocationHandle().bind(new World());
        world.sayWorld();
    }

}
