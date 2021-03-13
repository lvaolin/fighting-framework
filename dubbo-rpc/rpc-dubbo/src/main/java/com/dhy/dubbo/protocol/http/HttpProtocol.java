package com.dhy.dubbo.protocol.http;

import com.dhy.dubbo.dto.RpcRequest;
import com.dhy.dubbo.framework.Protocol;
import com.dhy.dubbo.framework.URL;

public class HttpProtocol implements Protocol {
    @Override
    public Object send(URL url, RpcRequest rpcRequest) {
        HttpClient httpClient = new HttpClient();
        String result = httpClient.send(url.getHost(), url.getPort(), rpcRequest);
        return result;
    }

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHost(),url.getPort());
    }
}
