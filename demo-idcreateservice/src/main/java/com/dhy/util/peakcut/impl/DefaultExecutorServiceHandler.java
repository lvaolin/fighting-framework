package com.dhy.util.peakcut.impl;

import com.dhy.util.peakcut.spi.ExecutorServiceHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultExecutorServiceHandler implements ExecutorServiceHandler {
    @Override
    public ExecutorService createExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        return executorService;
    }

}
