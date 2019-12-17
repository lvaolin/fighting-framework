package com.threadTest.lock.readwritelock;/**
 * Created by lvaolin on 17/11/3.
 */

/**
 * 只写线程
 *
 * @author lvaolin
 * @create 17/11/3 上午10:31
 */
public class WriteThread extends Thread {

    private Data data ;

    public WriteThread(String name,Data data){
        super(name);
        this.data = data;
    }
    @Override
    public void run() {
          while (true){
              data.write();
          }
    }
}
