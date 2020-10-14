package com.dhy.demo01;

import com.dhy.demo01.dto.User;
import com.dhy.demo01.mapper.UserMapper;
import com.dhy.demo01.mapper.UserMapperImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {


        UserMapper userMapper = (UserMapper)Proxy.newProxyInstance(Test.class.getClassLoader(), UserMapperImpl.class.getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy before");
                System.out.println("invoke target method");
                method.invoke(new UserMapperImpl(),args);
                System.out.println("proxy after");
                return new User();
            }
        });

        User user = userMapper.getUser();
        System.out.println(user.getName());

    }
}
