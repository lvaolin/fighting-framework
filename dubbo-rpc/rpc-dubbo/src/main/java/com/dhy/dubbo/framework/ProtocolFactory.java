package com.dhy.dubbo.framework;

import com.dhy.dubbo.protocol.http.HttpProtocol;

public class ProtocolFactory {

    public static Protocol getProtocol(){
         //获取到用户配置的协议名称或者id

        //使用spi机制加载对应的协议实现
        return new HttpProtocol();
    }
}
