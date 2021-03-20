package com.dhy.dubbo.provider;

import com.dhy.dubbo.common.IHelloService;
import org.junit.jupiter.api.Test;

class Main {

    @Test
    void test(){
        IHelloService helloService = new HelloServiceImpl();
        DubboProxyServer<IHelloService> proxyServer = new DubboProxyServer<>();
        proxyServer.exportService(helloService);
        System.out.println("服务端就绪！");
        for (;;);

    }

}
