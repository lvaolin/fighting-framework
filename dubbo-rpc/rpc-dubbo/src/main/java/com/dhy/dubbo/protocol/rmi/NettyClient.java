package com.dhy.dubbo.protocol.rmi;

import com.dhy.dubbo.dto.RpcRequest;

import java.io.*;
import java.net.Socket;

public class NettyClient {

    public Object send(String hostname, Integer port, RpcRequest rpcRequest){

        try {


            Socket socket = new Socket(hostname,port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(rpcRequest);
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // socket.
        return null;
    }
}
