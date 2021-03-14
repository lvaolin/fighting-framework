package com.dhy.dubbo.protocol.rmi;


import com.dhy.dubbo.framework.URL;
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

    private  String applicationName = "";
    public MyServer(String applicationName) throws Exception{
        if (applicationName==null) {
            throw new Exception("服务名称不能为空");
        }
        this.applicationName = applicationName;
    }

    public  void start(URL url) throws IOException {

        ServerSocket serverSocket = new ServerSocket(url.getPort());
        MyZkClient myZkClient = new MyZkClient();
        String rootName="/"+applicationName;
        //服务节点是否存在
        if (!myZkClient.exist(rootName)) {
            //创建
            myZkClient.createNode(rootName,"");
        }
        myZkClient.createEphemeralNode(rootName+"/"+url.getHost()+":"+url.getPort(),"");
        //追加本服务节点的地址信息 到下级临时节点
        System.out.println("向zookeeper注册服务提供者地址："+url.getHost()+":"+url.getPort());
        while (true) {
            System.out.println("服务端监听已准备好");
            Socket socket = serverSocket.accept();
            executor.execute(new MyTask(socket));
        }


    }
}
