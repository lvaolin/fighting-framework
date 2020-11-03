package com.dhy.dubbo.protocol.rmi;


import com.dhy.dubbo.register.zookeeper.zkutil.MyZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 启动socket监听
 * 接受请求数据
 * 响应数据
 */
public class MyServer {

    static Executor executor = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2));

    //static IUserServive userServive = new UserServiceImpl();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        MyZkClient myZkClient = new MyZkClient();
        myZkClient.createNode("/dhy-reg","127.0.0.1:8088");
        System.out.println("向zookeeper注册服务提供者地址："+"127.0.0.1:8088");
        while (true) {
            System.out.println("服务端监听已准备好");
            Socket socket = serverSocket.accept();
            executor.execute(new MyTask(socket));
        }


    }
}
