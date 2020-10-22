package com.dhy.util.peakcut.test;

import com.dhy.util.peakcut.TaskFacade;
import com.dhy.util.peakcut.TaskRequestDto;

import java.util.concurrent.TimeUnit;

public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        TaskFacade taskFacade = new TaskFacade();
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTaskPoolKey("openplatform.request1");
        taskRequestDto.setCallable(()->{
            System.out.println("call openplatform.request");
            TimeUnit.SECONDS.sleep(1);
            return "{name:111,order:123}";
        });
        taskRequestDto.getPoolConfig().setExecuteServiceHandler(MyExecutorServiceHandler.class.getCanonicalName());

        while (true){
            taskFacade.createTask(taskRequestDto);
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("排队数量："+taskFacade.getQueueSize(taskRequestDto.getTaskPoolKey()));
        }



//        synchronized (ClientTest.class){
//            ClientTest.class.wait();
//        }
    }
}
