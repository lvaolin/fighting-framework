package com.dhy.demo01_elasticjob.elasticjob;

import com.dangdang.ddframe.job.executor.handler.ExecutorServiceHandler;
import com.dangdang.ddframe.job.util.concurrent.ExecutorServiceObject;

import java.util.concurrent.ExecutorService;

public class MyExecutorServiceHandler implements ExecutorServiceHandler {
    private static final int MIN_THREAD_SIZE = 32;
    @Override
    public ExecutorService createExecutorService(final String jobName) {

        int threadSize =  Runtime.getRuntime().availableProcessors() * 2;
        //MIN_THREAD_SIZE是根据分片数量来的，这样可以保证即使在只有一台应用的情况下，即使开启流式处理情况下，即使并发很大的情况下，所有的分库消息都可以公平的均衡的处理掉
        //防止了消息高并发情况下，个别分片在流式计算的模型下一直占据有限的inner-job线程导致其他分片得不到消费消息的机会
        if(threadSize<=MIN_THREAD_SIZE){
            threadSize = MIN_THREAD_SIZE;
        }
        //threadSize = 1;
        return new ExecutorServiceObject("my-inner-job-" + jobName, threadSize).createExecutorService();
    }
}
