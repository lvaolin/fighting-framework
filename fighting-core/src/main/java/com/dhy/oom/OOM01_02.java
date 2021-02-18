package com.dhy.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 2个用户线程  其中一个发生内存溢出后，另一个正常线程会退出吗？ 不会
 *
 * @author lvaolin
 * @create 2019/12/26 4:24 PM
 */
public class OOM01_02 {

    private static List list = new ArrayList<byte[][]>();

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("正常线程---");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


        for(int i=0;i<12000;i++){
            list.add(new byte[1024][1024]);

            System.out.println("-----"+i+"-----");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
