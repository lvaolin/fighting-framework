package com.dhy.util.peakcut;

public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setTaskPoolKey("openplatform.request1");
        taskRequestDto.setCallable(()->{
                System.out.println("call openplatform.request");
                return "{name:111,order:123}";
        });
        TaskFacade.createTask(taskRequestDto);
        taskRequestDto.setTaskPoolKey("openplatform.request2");
        TaskFacade.createTask(taskRequestDto);

        synchronized (ClientTest.class){
            ClientTest.class.wait();
        }
    }
}
