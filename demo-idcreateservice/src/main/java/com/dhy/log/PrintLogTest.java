package com.dhy.log;

import com.mysql.jdbc.TimeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PrintLogTest {


    @SneakyThrows
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i <1 ; i++) {
            Thread thread = new Thread(new Runnable() {
                 @SneakyThrows
                 @Override
                 public void run() {
                     for (int i = 0; i <500000 ; i++) {
                         log.info(Thread.currentThread().getName()+"大量写日志测试cpu升高 /n  KKKKKKKKKKKKKKKKKKKK");
                     }
                 }
             });

            thread.start();
            threadList.add(thread);
        }


        for (Thread thread : threadList) {
            thread.join();
        }


        System.out.println("耗时："+(System.currentTimeMillis()-start));


    }
}
