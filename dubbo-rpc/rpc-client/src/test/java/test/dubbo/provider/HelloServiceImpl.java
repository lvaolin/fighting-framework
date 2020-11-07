package test.dubbo.provider;

public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }



}
