package com.dhy.server;

import com.dhy.dubbo.framework.URL;
import com.dhy.dubbo.protocol.dubbo.NettyServer;
import com.dhy.dubbo.protocol.rmi.MyServer;
import com.dhy.dubbo.protocol.rmi.RmiProtocol;

import java.io.IOException;

/**
 * @Title Main   提供者启动类
 * @Description
 * @Author lvaolin
 * @Date 2021/3/12 22:17
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        URL url = new URL("127.0.0.1",8088);
        //启动服务端 使用 rmi 协议
        RmiProtocol rmiProtocol = new RmiProtocol();
        rmiProtocol.start(url);
    }
}
