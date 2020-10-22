package com.dhy.util.peakcut.internal;

import com.dhy.util.peakcut.TaskParam;
import com.dhy.util.peakcut.TaskRequestDto;
import com.dhy.util.peakcut.impl.DefaultExecutorServiceHandler;
import com.dhy.util.peakcut.spi.ExecutorServiceHandler;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * 线程池管理器
 */
public class TaskThreadPoolManager {
    private volatile static Map<String,ExecutorService> executorServiceMap = new ConcurrentHashMap<>();

    public static ExecutorService getExecutorService(TaskRequestDto config){
        Assert.notNull(config.getTaskPoolKey(),"key不能为空");
        String key = config.getTaskPoolKey();
        ExecutorService executorService = executorServiceMap.get(key);
        if (executorService==null) {
            synchronized (TaskThreadPoolManager.class){
                if (executorService==null) {
                    executorService = createExecutorService(config);
                    executorServiceMap.put(key,executorService);
                }
            }
        }
        return executorService;
    }

    public static ExecutorService getExecutorServiceFromCache(String key){
        return executorServiceMap.get(key);
    }

    /**
     * 线程池扩展点
     * 1、如果 executeServiceHandler 配置参数存在 则使用调用者自己的线程池
     * 2、如果 poolSize不为null，则按参配置线程池
     * 3、默认返回默认的线程池
     * @param config
     * @return
     */
    private static ExecutorService createExecutorService(TaskRequestDto config){
        TaskRequestDto.ThreadPoolConfig poolConfig = config.getPoolConfig();
        if (poolConfig!=null) {
            if (poolConfig.getExecuteServiceHandler()!=null) {
                //加载类
                Class<?> handlerClass = null;
                try {
                    handlerClass = Class.forName(poolConfig.getExecuteServiceHandler());
                    ExecutorServiceHandler handler = (ExecutorServiceHandler)handlerClass.newInstance();
                    if (poolConfig.getPoolSize()!=null) {
                        return handler.createExecutorService(poolConfig.getPoolSize());
                    }else{
                        return handler.createExecutorService(null);
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return new DefaultExecutorServiceHandler().createExecutorService(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            }
            if (poolConfig.getPoolSize()!=null) {
                return new DefaultExecutorServiceHandler().createExecutorService(poolConfig.getPoolSize());
            }

        }

        return new DefaultExecutorServiceHandler().createExecutorService(null);

    }
}
