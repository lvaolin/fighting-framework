package com.dhy.demo.spring.jdbc.consumer;

import com.dhy.common.itf.ISeataStorageService;
import com.dhy.demo.spring.jdbc.common.IHelloService;
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

    @Test
    void test(){

        DubboProxy<ISeataStorageService> proxy = new DubboProxy<>();
        ISeataStorageService service = proxy.getService(ISeataStorageService.class);
        if (service instanceof EchoService) {
            EchoService echoService = (EchoService) service;
           // while (true){

                Object ok = echoService.$echo("ok");
                System.out.println(ok);
            //}
        }
    }

}
