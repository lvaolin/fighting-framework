package com.dhy.dubbo.framework;

import com.dhy.dubbo.dto.RpcRequest;
import com.dhy.dubbo.protocol.http.HttpClient;
import com.dhy.dubbo.protocol.http.HttpProtocol;
import com.dhy.dubbo.register.zookeeper.zkutil.MyZkClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private String applicationName;
    public MyInvocationHandler(String applicationName){
         this.applicationName = applicationName;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //发起rpc调用
        //准备rpc请求的参数
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setApplicationName(applicationName);
        //获取类名称
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameterValues(args);
        rpcRequest.setParameterTypes(method.getParameterTypes());

        Object object = rpcInvoke(rpcRequest);

        //得到结果返回给调用者
        return object;
    }

    private Object rpcInvoke(RpcRequest rpcRequest) throws IOException, ClassNotFoundException {

        MyZkClient myZkClient = new MyZkClient();
        String host = myZkClient.queryData("/"+rpcRequest.getApplicationName());
        System.out.println("从zookeeper注册中心获取到服务提供者地址："+host);
        String[] split = host.split(":");

        URL url = new URL();
        url.setHost(split[0]);
        url.setPort( Integer.parseInt(split[1]));

        //根据用户的配置决定使用的协议 @todo
        Protocol protocol = ProtocolFactory.getProtocol();
        Object result = protocol.send(url, rpcRequest);
        return result;
    }
}
