package com.dhy.util.peakcut.test;

import com.dhy.util.peakcut.TaskFacade;
import com.dhy.util.peakcut.TaskRequestDto;

public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTaskPoolKey("openplatform.request1");
        taskRequestDto.setCallable(()->{
                System.out.println("call openplatform.request");
                return "{name:111,order:123}";
        });
        TaskFacade taskFacade = new TaskFacade();
        taskFacade.createTask(taskRequestDto);
        taskRequestDto.setTaskPoolKey("openplatform.request2");
        taskFacade.createTask(taskRequestDto);

        synchronized (ClientTest.class){
            ClientTest.class.wait();
        }
    }
}
