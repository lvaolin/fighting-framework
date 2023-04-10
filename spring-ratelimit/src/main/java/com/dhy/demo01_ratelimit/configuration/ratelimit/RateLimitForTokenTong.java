package com.dhy.demo01_ratelimit.configuration.ratelimit;


import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法
 * 桶的容量最大容量是固定的，比如是1000个并发，
 * 一个请求必须获取成功一个permit才能通过，否则等待；
 *
 * 固定速率向桶里注入令牌 比如100个/秒，最多可积累1000个令牌
 *
 * 相对于漏桶算法的优势是可以承受突发流量，最大值就是桶的最大容量1000
 *
 */
public class RateLimitForTokenTong {

    private static Map<String, Semaphore> map = new ConcurrentHashMap<>();

    /**
     * 获得许可
     * @param url
     * @return
     */
    public static  boolean  acquirePermit(String url){
        if(map.get(url)==null){
            synchronized (RateLimitForTokenTong.class){
                if(map.get(url)==null){
                    map.put(url,new Semaphore(10));
                }
            }
        }
        try {
            System.out.println("申请许可前："+map.get(url).availablePermits());
            //超时时间3秒，3秒内获得了许可返回true，超时返回false
            return map.get(url).tryAcquire();
        } catch (Exception e) {
            //中断异常 返回false
            return false;
        }
    }

    /**
     * 释放许可
     * @param url
     */
    public static  void   releasePermit(String url){
        if(map.get(url)!=null){
            map.get(url).release();
            System.out.println("释放许可后："+map.get(url).availablePermits());
        }
    }


    public static void main(String[] args) {
        //测试

        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "";
                    while (true){
                        url = "/v1/"+new Random().nextInt(10)+"/xxx/a/";
                        boolean flag = false;
                        if (RateLimitForTokenTong.acquirePermit(url)) {
                            System.out.println(url+" passed");
                            flag = true;
                        }else{
                            System.out.println(url+" passed nonono");
                            flag = false;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(flag){
                            //注意释放许可是没有任何条件的，释放后会加1，我们约定没有获取许可成功的线程不能释放许可
                            //否则可能导致 可用许可大于 初始值设置的许可最大值
                            RateLimitForTokenTong.releasePermit(url);
                        }

                    }
                }
            }).start();

        }
    }

}
