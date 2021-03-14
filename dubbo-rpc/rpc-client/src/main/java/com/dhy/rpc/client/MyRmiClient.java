package com.dhy.rpc.client;

import com.dhy.dubbo.framework.ProxyFactory;
import com.dhy.server.itf.IUserServive;
import com.dhy.server.dto.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MyRmiClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //通过jdk代理生成一个IUserServive代理对象
        ProxyFactory<IUserServive> proxyFactory = new ProxyFactory<IUserServive>();
        IUserServive userServive= proxyFactory.getProxy("user-service",IUserServive.class);
        System.out.println("获取代理对象成功：");

        FutureTask futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                while (true){
                    //调用方法
                    try {
                        System.out.println("调用方法before");
                        User user = userServive.getUserById(100L);
                        System.out.println("调用方法after");
                        System.out.println(user);
                        TimeUnit.SECONDS.sleep(3);
                    }catch (Exception e){
                        TimeUnit.SECONDS.sleep(3);
                    }

                }
            }
        });
        futureTask.run();
        futureTask.get();

        synchronized (MyRmiClient.class){
            try {
                MyRmiClient.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
