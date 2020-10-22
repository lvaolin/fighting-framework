package com.dhy.util.peakcut.spi;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceHandler {
    ExecutorService createExecutorService();

}
