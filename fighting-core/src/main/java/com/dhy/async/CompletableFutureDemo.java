package com.dhy.async;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @Title FutureTaskDemo
 * @Description
 * @Author lvaolin
 * @Date 2021/3/14 23:20
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("--completable future   1---------");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        voidCompletableFuture.thenRun(() -> {
            System.out.println("----2----");
        }).get();


    }
}
