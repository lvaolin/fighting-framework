package com.dhy.socket.nio;

import java.io.*;
import java.net.Socket;

/**
 * Java NIO测试 客户端
 *
 * @author lvaolin
 * @create 2019/11/11 8:17 PM
 */
public class NioTestClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("127.0.0.1",9090);

            client.setSendBufferSize(20);
            client.setTcpNoDelay(true);
            OutputStream out = client.getOutputStream();

            OutputStream fileout = new FileOutputStream(new File("c:/downloadlimit.txt"));
            InputStream serverIn = client.getInputStream();

            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true){
                String line = reader.readLine();
                if(line != null ){
                    byte[] bb = line.getBytes();
                    out.write(bb);
                }
                if ("download".equals(line)) {
                    //serverIn.transferTo(fileout);
                }else{
                    int len =0;
                    while ( (len = serverIn.read())>0){
                        //System.out.println(serverIn.readNBytes(len).toString());
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
