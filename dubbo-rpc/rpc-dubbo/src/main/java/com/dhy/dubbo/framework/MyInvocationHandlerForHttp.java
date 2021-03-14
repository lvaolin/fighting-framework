package com.dhy.dubbo.framework;

import com.dhy.dubbo.dto.RpcRequest;
import com.dhy.dubbo.protocol.http.HttpClient;
import com.dhy.dubbo.register.zookeeper.zkutil.MyZkClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandlerForHttp implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //发起rpc调用
        //准备rpc请求的参数
        RpcRequest rpcRequest = new RpcRequest();
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
        String host = myZkClient.getData("/dhy-reg");
        System.out.println("从zookeeper注册中心获取到服务提供者地址："+host);
        String[] split = host.split(":");


        HttpClient httpClient = new HttpClient();
        String result = httpClient.send(split[0], Integer.parseInt(split[1]), rpcRequest);
        return result;
    }
}
