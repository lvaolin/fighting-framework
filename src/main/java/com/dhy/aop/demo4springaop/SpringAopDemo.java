package com.dhy.aop.demo4springaop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Spring Aop 类实现代理
 *
 * @author lvaolin
 * @create 2020/1/22 3:08 PM
 */
public class SpringAopDemo {

    public static void main(String[] args) {

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImpl());
        proxyFactory.addAdvice(new HelloBeforeAdvice());
        proxyFactory.addAdvice(new HelloAfterAdvice());
        IHello proxy = (IHello)proxyFactory.getProxy();
        proxy.say("大黄鸭");


    }

}
