package com.dhy.concurrency.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by admin on 2017/10/14.
 */
public class PrintLog {

     static List<String>  logList = new ArrayList<String>();
    static  CyclicBarrier cyclicBarrier = new CyclicBarrier(11);
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    while (true){
                        try {
                            if(logList.size()>0){
                                String log=null;
                                synchronized (logList){
                                    if(logList.size()>0){
                                        log = logList.get(0);
                                        logList.remove(0);
                                    }
                                }
                                if(log!=null){
                                    parseLog(log);
                                }

                            }else{
                                System.out.println("没有任务了。");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        for (int i = 0; i <1000000 ; i++) {//不能改动
            final String log = ""+(i+1);//不能改动
            // PrintLog.parseLog(log);
            synchronized (logList){
                logList.add(log);
            }

        }

        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    //方法内容不能改动
  public   static void parseLog(String log) throws InterruptedException {
      System.out.println(log+":"+System.currentTimeMillis()/1000);
      TimeUnit.SECONDS.sleep(1);
    }
}
