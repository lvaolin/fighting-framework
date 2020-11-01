package com.dhy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 启动socket监听
 * 接受请求数据
 * 响应数据
 */
public class Main {

    static Executor executor =  new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(2));


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("服务端监听已准备好");
            Socket socket = serverSocket.accept();
            executor.execute(new Task(socket));
        }


    }
}
