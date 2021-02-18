package com.dhy.concurrency.forkjoin;/**
 * Created by lvaolin on 17/10/16.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 快速生成大量的uuid
 *
 * @author lvaolin
 * @create 17/10/16 下午1:26
 */
public class CreateIDService extends RecursiveTask<List> {


    private long idcount = 0;
    private int thredholds=100;//默认值
    public CreateIDService(long idcount,int thredholds){
        this.idcount = idcount;
        this.thredholds = thredholds;
    }



    @Override
    protected List compute() {
         List<String> list = new ArrayList<String>();
        boolean canCompute = (idcount<=thredholds);
        if(canCompute){
            String tuuid = null;
            for (int i = 0; i <idcount ; i++) {
               /* try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                tuuid = UUID.randomUUID().toString();
                list.add(tuuid);
                System.out.println(Thread.currentThread().getName()+"："+tuuid);
            }
        }else{

            long middle = (idcount)/2;
            CreateIDService createIDServiceLeft = new CreateIDService(middle,thredholds);
            CreateIDService createIDServiceRight = new CreateIDService(middle,thredholds);
            invokeAll(createIDServiceLeft,createIDServiceRight);
            try {
               list =  mergeResult(createIDServiceLeft.get(),createIDServiceRight.get());//如果有必要可以合并结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }


        return list;
    }


    List<String> mergeResult(List<String> leftList,List<String> rightList){
        List<String> list = new ArrayList<String>();
        list.addAll(leftList);
        list.addAll(rightList);
        return list;
    }


    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long starttime = System.currentTimeMillis();
        CreateIDService createIDService = new CreateIDService(1000000,100);
        forkJoinPool.execute(createIDService);
        try {
            List list = createIDService.get();
            System.out.println("AAAAAA:"+list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("用时："+(System.currentTimeMillis()-starttime));

    }
}
