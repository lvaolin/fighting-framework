package com.dhy.leetcode.ratelimit;

import lombok.SneakyThrows;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流算法
 * 实现一个固定窗口限流算法
 * 同一个url每1秒钟限制调用次数为1000次，剩下的返回false
 */
public class RateLimitForFixedCount {
    private static  Map<String, AtomicInteger> map = new ConcurrentHashMap<>();
    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true&&!Thread.interrupted()){
                    map.entrySet().forEach((entry)->{
                        System.out.println(entry.getKey()+"----->"+entry.getValue().get());
                        entry.getValue().set(0);
                    });
                    try {
                        //休眠1秒钟
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public boolean rateLimit(String url){
        if (map.get(url)==null) {
            synchronized (this){
                if(map.get(url)==null){
                    map.put(url,new AtomicInteger(1));
                    return true;
                }
            }
        }
        int i = map.get(url).incrementAndGet();
        if (i>10) {
            return  false;
        }
        return true;
    }

    public static void main(String[] args) {
        RateLimitForFixedCount rateLimitForFixedCount = new RateLimitForFixedCount();
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    String url = "";
                    while (true){
                        url = "/v1/"+new Random().nextInt(10)+"/xxx/a/";
                        if (rateLimitForFixedCount.rateLimit(url)) {
                            System.out.println(url+" passed");
                        }else{
                            System.out.println(url+" passed nonono");
                        }
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                }
            }).start();

        }


    }
}
