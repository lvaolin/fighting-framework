package com.dhy.demo01.mapper;

import com.dhy.demo01.dto.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserMapperInvocationHandler implements InvocationHandler {
    Object targetObject = new UserMapperImpl();
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy before");
        System.out.println("invoke target method");
        method.invoke(targetObject,args);
        System.out.println("proxy after");
        return new User();
    }
}
