package com.dhy.dubbo.protocol.rmi;

import com.dhy.dubbo.dto.RpcRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NettyClient {

    public String send(String hostname, Integer port, RpcRequest rpcRequest){

        try {
            Socket socket = new Socket(hostname,port);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("dahuangya".getBytes());
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // socket.
        return null;
    }
}
