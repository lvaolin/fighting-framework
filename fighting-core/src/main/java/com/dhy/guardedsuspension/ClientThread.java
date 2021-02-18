package com.dhy.guardedsuspension;/**
 * Created by lvaolin on 17/11/1.
 */

/**
 * 模拟客户端（请求端）线程
 *
 * @author lvaolin
 * @create 17/11/1 下午2:12
 */
public class ClientThread  extends Thread{
    private final RequestQueue requestQueue ;


    public  ClientThread(RequestQueue requestQueue ){
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true&&!Thread.currentThread().isInterrupted()){
           /* try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            requestQueue.putRequest(System.currentTimeMillis()+"");
        }

    }
}
