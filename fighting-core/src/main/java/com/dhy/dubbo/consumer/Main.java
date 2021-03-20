package com.dhy.dubbo.consumer;

import com.dhy.dubbo.common.IHelloService;
import org.apache.dubbo.rpc.service.EchoService;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class Main {

    @Test
    void hello(){

        DubboProxy<IHelloService> proxy = new DubboProxy<>();
        IHelloService service = proxy.getService(IHelloService.class);
        String dahuangya = service.hello("dahuangya");
        System.out.println("dahuangya:"+dahuangya);
        if (service instanceof EchoService) {
            EchoService echoService = (EchoService) service;
            Object ok = echoService.$echo("ok");
            System.out.println(ok);
        }
        for (;;);
    }


}
