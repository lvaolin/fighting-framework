package com.dhy.rpc.client;

import com.dhy.dubbo.framework.ProxyFactory;
import com.dhy.server.itf.IUserServive;
import com.dhy.server.dto.User;

public class MyRmiClient {
    public static void main(String[] args) {
        for (int i = 0; i <1 ; i++) {
            //通过jdk代理生成一个IUserServive代理对象
            ProxyFactory<IUserServive> proxyFactory = new ProxyFactory<IUserServive>();
            IUserServive userServive= proxyFactory.getProxy(IUserServive.class);
            System.out.println("获取代理对象成功：");
            //调用方法
            System.out.println("调用方法before");
            User user = userServive.getUserById(1L);
            System.out.println("调用方法after");
            System.out.println(user);
        }

    }
}
