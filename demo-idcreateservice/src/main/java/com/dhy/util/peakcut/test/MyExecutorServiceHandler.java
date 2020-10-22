package com.dhy.util.peakcut.test;

import com.dhy.util.peakcut.spi.ExecutorServiceHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MyExecutorServiceHandler implements ExecutorServiceHandler {
    @Override
    public ExecutorService createExecutorService(Integer poolSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        return executorService;
    }

}
