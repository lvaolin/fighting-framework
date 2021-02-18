package com.dhy.redis;

import java.io.IOException;
import java.net.Socket;

public class RedisClient {

      private Socket connection;

      public RedisClient(String host,int port) throws IOException {
            connection = new Socket(host,port);
      }

      public  String set(String key,String value) throws IOException{
            System.out.println("开始报文组装---");
            StringBuilder request = new StringBuilder();
            request.append("*3").append("\r\n");
            request.append("$3").append("\r\n");
            request.append("set").append("\r\n");
            request.append("$").append(key.getBytes().length).append("\r\n");
            request.append(key).append("\r\n");
            request.append("$").append(value.getBytes().length).append("\r\n");
            request.append(value).append("\r\n");
            System.out.println("报文组装完毕---");
            System.out.println(request.toString());
            connection.getOutputStream().write(request.toString().getBytes("UTF-8"));
            byte[] buffer = new byte[1024];
            connection.getInputStream().read(buffer);
            System.out.println("响应结果："+new String(buffer,"UTF-8"));
            return new String(buffer);
      }

      public  String get(String key) throws IOException{
            System.out.println("开始报文组装---");
            StringBuilder request = new StringBuilder();
            request.append("*2").append("\r\n");
            request.append("$3").append("\r\n");
            request.append("get").append("\r\n");
            request.append("$").append(key.getBytes().length).append("\r\n");
            request.append(key).append("\r\n");
            System.out.println("报文组装完毕---");
            System.out.println(request.toString());
            connection.getOutputStream().write(request.toString().getBytes());
            byte[] buffer = new byte[1024];
            connection.getInputStream().read(buffer);
            System.out.println("响应结果："+new String(buffer));
            return new String(buffer);
      }

}
