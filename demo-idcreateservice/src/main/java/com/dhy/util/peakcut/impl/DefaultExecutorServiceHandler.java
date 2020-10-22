package com.dhy.util.peakcut.impl;

import com.dhy.util.peakcut.spi.ExecutorServiceHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultExecutorServiceHandler implements ExecutorServiceHandler {
    private int defaultPoolSize = 100;
    @Override
    public ExecutorService createExecutorService(Integer poolSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize!=null?poolSize.intValue():defaultPoolSize);
        return executorService;
    }

}
