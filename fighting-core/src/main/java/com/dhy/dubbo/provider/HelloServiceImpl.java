package com.dhy.dubbo.provider;


import com.dhy.dubbo.common.IHelloService;

/**
 * @Title HelloServiceImpl
 * @Description
 * @Author lvaolin
 * @Date 2021/3/20 21:27
 **/
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello(String name) {
        return "hello,"+name;
    }
}
