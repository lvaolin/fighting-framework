package com.dhy.dubbo.framework;

import com.dhy.dubbo.dto.RpcRequest;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

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

        //获取提供者主机列表
        List<String> hostList = HostFactory.getHosts(rpcRequest);
        //负载均衡
        String host = LoadBalanceFactory.loadBalance(hostList);

        String[] split = host.split(":");
        URL url = new URL();
        url.setHost(split[0]);
        url.setPort( Integer.parseInt(split[1]));

        //获取协议
        Protocol protocol = ProtocolFactory.getProtocol();
        Object result = protocol.send(url, rpcRequest);
        return result;
    }




}
