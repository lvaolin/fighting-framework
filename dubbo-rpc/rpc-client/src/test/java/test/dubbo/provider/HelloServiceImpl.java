package test.dubbo.provider;

import test.dubbo.common.IHelloService;

public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }



}
