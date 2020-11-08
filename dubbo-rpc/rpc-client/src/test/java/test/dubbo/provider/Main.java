package test.dubbo.provider;


import org.apache.dubbo.config.spring.ServiceBean;
import org.apache.dubbo.rpc.proxy.InvokerInvocationHandler;
import test.dubbo.common.IHelloService;
import test.dubbo.common.TestUtil;

public class Main {

    public static void main(String[] args) {
        TestUtil.exportService(IHelloService.class,new HelloServiceImpl());
        System.out.println("service provider is ready");
        synchronized (Main.class){
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
