package com.dhy.concurrency.threadTest.httpserver;/**
 * Created by lvaolin on 17/10/10.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个基于线程池技术的简单web服务器
 *
 * @author lvaolin
 * @create 17/10/10 下午1:14
 */
public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);
    static ServerSocket serverSocket;
    static int port = 8080;

    public static void setPort(int port){
        if(port>0){
            SimpleHttpServer.port = port;
        }

    }

    public static  void start() throws IOException {

        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept())!=null){
             //处理socket
           threadPool.execute(new HttpRequestHandler(socket));
        }

        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable{

        private Socket socket;

        public  HttpRequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
             //处理socket   接收输入参数  然后响应

        }


        private static void close(){
            //关闭流和socket
        }
    }
}
