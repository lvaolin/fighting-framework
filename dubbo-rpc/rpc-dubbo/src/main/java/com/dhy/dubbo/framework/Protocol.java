package com.dhy.dubbo.framework;

import com.dhy.dubbo.dto.RpcRequest;

public interface Protocol {

    Object send(URL url, RpcRequest rpcRequest);
    void start(URL url);

}
