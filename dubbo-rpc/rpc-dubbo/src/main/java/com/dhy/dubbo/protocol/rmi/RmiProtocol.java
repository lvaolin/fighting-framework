package com.dhy.dubbo.protocol.rmi;

import com.dhy.dubbo.dto.RpcRequest;
import com.dhy.dubbo.framework.Protocol;
import com.dhy.dubbo.framework.URL;

import java.io.IOException;

public class RmiProtocol implements Protocol {
    @Override
    public Object send(URL url, RpcRequest rpcRequest) {
        return new NettyClient().send(url.getHost(), url.getPort(), rpcRequest);
    }

    @Override
    public void start(URL url) {
        try {
            MyServer.start(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
