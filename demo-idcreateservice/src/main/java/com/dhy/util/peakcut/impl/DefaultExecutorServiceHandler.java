package com.dhy.util.peakcut.impl;

import com.dhy.util.peakcut.spi.ExecutorServiceHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultExecutorServiceHandler implements ExecutorServiceHandler {
    /**
     * @todo 高峰期可上去，低谷期可下来
     */
    private int defaultPoolSize = 50;
    @Override
    public ExecutorService createExecutorService(Integer poolSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize!=null?poolSize.intValue():defaultPoolSize);
        return executorService;
    }

}
