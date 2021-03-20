package com.dhy.demo.spring.jdbc.provider;

import com.dhy.demo.spring.jdbc.common.IHelloService;
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
