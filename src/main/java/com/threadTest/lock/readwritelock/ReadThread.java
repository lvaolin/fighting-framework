package com.threadTest.lock.readwritelock;/**
 * Created by lvaolin on 17/11/3.
 */

/**
 * 只读线程
 *
 * @author lvaolin
 * @create 17/11/3 上午10:31
 */
public class ReadThread extends Thread {


    private Data data ;

    public ReadThread(String name,Data data){
        super(name);
        this.data = data;
    }

    @Override
    public void run() {

        while (true){
            data.read();
        }

    }
}
