package com.dhy.guardedsuspension;/**
 * Created by lvaolin on 17/11/1.
 */

import java.util.concurrent.TimeUnit;

/**
 * 测试类
 *
 * @author lvaolin
 * @create 17/11/1 下午2:19
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        RequestQueue requestQueue = new RequestQueue();

       ClientThread clientThread =  new ClientThread(requestQueue);
       ServerThread serverThread =  new ServerThread(requestQueue);

       clientThread.start();
       serverThread.start();

        TimeUnit.SECONDS.sleep(3);

        clientThread.interrupt();
        serverThread.interrupt();
        System.out.println("-------------calling  interrupt----------");

    }
}
