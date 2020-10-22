package com.dhy.util.peakcut.internal;

import com.dhy.util.peakcut.impl.DefaultExecutorServiceHandler;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * 线程池管理器
 */
public class TaskThreadPoolManager {
    private volatile static Map<String,ExecutorService> executorServiceMap = new ConcurrentHashMap<>();

    public static ExecutorService getExecutorService(String key){
        Assert.notNull(key,"key不能为空");
        ExecutorService executorService = executorServiceMap.get(key);
        if (executorService==null) {
            synchronized (TaskThreadPoolManager.class){
                if (executorService==null) {
                    executorService = createExecutorService();
                    executorServiceMap.put(key,executorService);
                }
            }
        }
        return executorService;
    }

    private static ExecutorService createExecutorService(){
        //扩展点
        return new DefaultExecutorServiceHandler().createExecutorService();
    }
}
