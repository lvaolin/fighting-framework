package com.dhy.server;

import com.dhy.server.impl.MyBeanFactory;
import com.dhy.server.impl.UserServiceImpl;
import com.dhy.server.itf.IUserServive;
import com.dhy.server.dto.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class MyTask implements Runnable {
    Socket socket;
    public MyTask(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            System.out.println("接收到 remote call");
            //获取从客户端上传的数据
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            //反射调用某个类的某个方法，需要 类名+方法名+参数名称+参数类型
            Object result = doInvoke(rpcRequest);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectInputStream.close();
            socket.close();
            System.out.println("remote doInvoke end");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object doInvoke(RpcRequest rpcRequest) throws Exception {
        System.out.println(rpcRequest.toString());
        Class clazz =Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
        Object result = method.invoke(getService(rpcRequest),rpcRequest.getParameterValues());
        return  result;
    }

    public Object getService(RpcRequest rpcRequest){
        //获取要执行方法的 业务对象
        return MyBeanFactory.getInstance().getBean(rpcRequest.getClassName());
    }
}
