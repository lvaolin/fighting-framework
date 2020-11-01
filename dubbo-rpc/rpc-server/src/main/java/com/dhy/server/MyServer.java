package com.dhy.server;

import com.dhy.server.impl.UserServiceImpl;
import com.dhy.server.itf.IUserServive;

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

    static IUserServive userServive = new UserServiceImpl();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("服务端监听已准备好");
            Socket socket = serverSocket.accept();
            executor.execute(new MyTask(socket));
        }


    }
}
