package com.aop.hello;

/**
 * @author lvaolin
 * @create 2019/7/15 6:47 PM
 */
public class AopTest {
    public static void main(String[] args) {
        IHello hello =(IHello) new MyInvocationHandle().bind(new HelloImpl());
        hello.sayHello("马克");
        hello.sayMorning("菠萝");
        hello.sayOk("纳米");
    }

}
