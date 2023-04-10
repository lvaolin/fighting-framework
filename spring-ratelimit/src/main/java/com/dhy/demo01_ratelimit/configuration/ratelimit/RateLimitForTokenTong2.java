package com.dhy.demo01_ratelimit.configuration.ratelimit;


import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
public class RateLimitForTokenTong2 {

    private static long lastUpdateTime = System.currentTimeMillis();
    private static AtomicLong x = new AtomicLong();

    /**
     * 获得许可
     * @param url
     * @return
     */
    public static  boolean  acquirePermit(String url){
        long nowTime = System.currentTimeMillis();
        if (nowTime!=lastUpdateTime) {
            long passTime = nowTime - lastUpdateTime;
            long nowLimit = x.addAndGet(passTime * 1);
            if (nowLimit>5) {
                x.set(5);
            }
            lastUpdateTime= nowTime;
        }
        if (x.decrementAndGet()>=0) {
            return true;
        }else{
            return false;
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
                        if (RateLimitForTokenTong2.acquirePermit(url)) {
                            System.out.println(url+" passed");
                            flag = true;
                        }else{
                            System.out.println(url+" passed nonono");
                            flag = false;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }).start();

        }
    }

}
