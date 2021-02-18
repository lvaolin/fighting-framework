package com.dhy.concurrency.executor;/**
 ExecutorCompletionService:实现了CompletionService，将执行完成的任务放到阻塞队列中，通过take或poll方法来获得执行结果
 * Created by lvaolin on 17/10/18.
 */

import java.util.concurrent.*;

/**
 * @author lvaolin
 * @create 17/10/18 下午2:29
 */
public class CompletionServiceTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10);        //创建含10.条线程的线程池
        CompletionService completionService = new ExecutorCompletionService(executor);
        for (int i =1; i <=10; i ++) {
            final  int result = i;
            completionService.submit(new Callable() {
                public Object call() throws Exception {

                    return result;
                }
            });
        }
        System.out.println(completionService.take().get());   //获取执行结果
        executor.shutdownNow();


    }


    public synchronized void method(){

    }

}
