package com.dhy.dubbo.framework;


import com.dhy.dubbo.dto.RpcRequest;
import com.dhy.dubbo.register.zookeeper.zkutil.MyZkClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class MyInvocationHandlerForRmi implements InvocationHandler {
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
        String host = myZkClient.queryData("/dhy-reg");
        System.out.println("从zookeeper注册中心获取到服务提供者地址："+host);
        String[] split = host.split(":");

        Socket socket = new Socket(split[0],Integer.parseInt(split[1]));

        //向服务端发送数据
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(rpcRequest);
        outputStream.flush();

        //接收响应的数据
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object object = inputStream.readObject();

        //关闭通信资源
        outputStream.close();
        inputStream.close();
        socket.close();
        return object;
    }
}
