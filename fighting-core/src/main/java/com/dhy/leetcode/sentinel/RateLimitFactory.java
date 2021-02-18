package com.dhy.leetcode.sentinel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitFactory {
    public static int defaultWinSize = 10;
    //当前窗口请求数量
    public static  final AtomicInteger requestCount = new AtomicInteger();
    //能否通过的标志
    public static volatile boolean pass = true;

    static {
        //定时刷新窗口数据
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (RateLimitFactory.isOverLimit("http://xxx.com/a/b", requestCount.intValue())) {
                    pass = false;
                } else {
                    pass = true;
                }
                requestCount.set(0);
            }
        }, 0L, 1000 / RateLimitFactory.defaultWinSize, TimeUnit.MILLISECONDS);
    }

    private static Map<String,RateLimitObject> limitObjectMap = new ConcurrentHashMap<>();
    public static boolean isOverLimit(String url,int curCount,int windowSize){
        if (url==null||"".equals(url)) {
            throw new RuntimeException("url不能为空");
        }
        RateLimitObject rateLimitObject = limitObjectMap.get(url);
        if (rateLimitObject==null) {
            rateLimitObject = new RateLimitObject(windowSize);
            limitObjectMap.put(url,rateLimitObject);
        }
        return  rateLimitObject.isOverLimit(curCount);
    }

    public static boolean isOverLimit(String url,int curCount){
        return isOverLimit(url,curCount,defaultWinSize);
    }


}
