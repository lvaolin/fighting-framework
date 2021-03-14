package com.dhy.rpc.client;

import com.dhy.dubbo.framework.ProxyFactory;
import com.dhy.server.itf.IUserServive;
import com.dhy.server.dto.User;

public class MyRmiClient {
    public static void main(String[] args) {
        //通过jdk代理生成一个IUserServive代理对象
        ProxyFactory<IUserServive> proxyFactory = new ProxyFactory<IUserServive>();
        IUserServive userServive= proxyFactory.getProxy("user-service",IUserServive.class);
        System.out.println("获取代理对象成功：");
        for (long i = 0; i <1000 ; i++) {
            //调用方法
            System.out.println("调用方法before");
            User user = userServive.getUserById(i);
            System.out.println("调用方法after");
            System.out.println(user);
        }

        synchronized (MyRmiClient.class){
            try {
                MyRmiClient.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
