package com.dhy.server.impl;

import com.dhy.server.itf.IUserServive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyBeanFactory {
    static MyBeanFactory myBeanFactory = new MyBeanFactory();
    Map<String,Object> beanMap = new ConcurrentHashMap<>();
    private MyBeanFactory(){
        beanMap.put(IUserServive.class.getName(),new UserServiceImpl());
    }
    public Object getBean(String beanName){
        System.out.println(beanName);
        return beanMap.get(beanName);
    }
    public static MyBeanFactory getInstance(){
        return myBeanFactory;
    }
}
