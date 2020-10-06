package com.dhy.leetcode.sentinel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitFactory {
    public static int defaultWinSize = 100;
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
