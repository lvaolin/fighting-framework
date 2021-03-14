package com.dhy.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title FutureTaskDemo
 * @Description
 * @Author lvaolin
 * @Date 2021/3/14 23:20
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return true;
            }
        });
        booleanFutureTask.run();
        Boolean aBoolean = booleanFutureTask.get();
        System.out.println(aBoolean);


    }
}
