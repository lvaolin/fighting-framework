package com.dhy.dubbo.protocol.dubbo;

import com.dhy.dubbo.dto.RpcRequest;

public class NettyClient {

    public String send(String hostname, Integer port, RpcRequest rpcRequest){
        System.out.println("发送成功");
        return "ok";
    }
}
