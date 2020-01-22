package com.dhy.aop.demo4jdkproxy;

/**
 * 代理目标类
 *
 * @author lvaolin
 * @create 2020/1/6 4:25 PM
 */
public class World implements IWorld{
    @Override
    public void sayWorld(){
        System.out.println("world cglib  proxy");
    }
}
