package com.dhy.temp;

import java.util.concurrent.*;

/**
 * 测试下线程池的参数动态变更：
 * 队列容量、corePoolSize、maxPoolSize，
 * 定制ThreadFactory，拒绝策略，
 * timeout
 * timeunit
 *
 *   public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler) {
 */
public class Test20201025 {

   static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,10, TimeUnit.SECONDS,
            new LinkedBlockingQueue(5), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //不断发送任务
                    try {
                        threadPoolExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.print("running@");
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }catch (Exception e){
                        System.out.println("task rejecked");
                    }

                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //30秒后扩容线程池容量
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPoolExecutor.setMaximumPoolSize(20);
                LinkedBlockingQueue<Runnable> queue = (LinkedBlockingQueue)threadPoolExecutor.getQueue();

            }
        }).start();


        synchronized (Test20201025.class){
            try {
                Test20201025.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
