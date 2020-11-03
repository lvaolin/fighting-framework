package com.dhy.dubbo.framework;

import com.dhy.dubbo.dto.RpcRequest;

public interface Protocol {

    String send(URL url, RpcRequest rpcRequest);
    void start(URL url);

}
