package com.dhy.server;

import com.dhy.server.impl.UserServiceImpl;
import com.dhy.server.itf.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Task implements Runnable {
    Socket socket;
    public Task(Socket socket){
        this.socket = socket;
    }
    public void run() {
        try {
            System.out.println("接收到 remote call");
            //获取从客户端上传的数据
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            //反射调用某个类的某个方法，需要 类名+方法名+参数名称+参数类型
            Class<?> c =UserServiceImpl.class;
            Method method = c.getMethod(rpcRequest.getMethodName());
            Object result = method.invoke(rpcRequest.getParameterValues());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectInputStream.close();
            socket.close();
            System.out.println("remote invoke end");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
