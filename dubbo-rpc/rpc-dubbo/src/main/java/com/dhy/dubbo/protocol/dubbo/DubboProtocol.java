package com.dhy.dubbo.protocol.dubbo;

import com.dhy.dubbo.dto.RpcRequest;
import com.dhy.dubbo.framework.Protocol;
import com.dhy.dubbo.framework.URL;

public class DubboProtocol implements Protocol {
    @Override
    public String send(URL url, RpcRequest rpcRequest) {
        return new NettyClient().send(url.getHost(), url.getPort(), rpcRequest);
    }

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHost(),url.getPort());
    }
}
