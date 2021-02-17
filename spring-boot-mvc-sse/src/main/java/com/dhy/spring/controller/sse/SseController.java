package com.dhy.spring.controller.sse;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title SseController
 * @Description  sse 机制测试   server sent event
 * @Author lvaolin
 * @Date 2021/2/16 18:51
 **/
@RestController
@RequestMapping("/sse")
public class SseController {

    //模拟新数据
    String newUserId =  UUID.randomUUID().toString();

    @RequestMapping(value = "/getTime",produces = "text/event-stream;charset=utf-8")
    public String getTime(String userId) throws InterruptedException {
        System.out.println("sse getTime ------");
        TimeUnit.SECONDS.sleep(60);
        LocalDateTime now = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        sb.append("id:1 \n");
        sb.append("event:time\n");
        sb.append("retry:3000\n");
        sb.append("data:"+now.toString()+" \n");
        sb.append("data:"+now.toString()+" \n\n");
        return sb.toString();
    }

    @RequestMapping(value = "/getMessage",produces = "text/event-stream;charset=utf-8")
    public String getMessage(String userId) throws InterruptedException {
        System.out.println("sse  getMessage ------");
        //TimeUnit.SECONDS.sleep(60);

        String newMsg = getNewMessage();

        StringBuilder sb = new StringBuilder();
        sb.append("id:2 \n");
        sb.append("event:message\n"); //默认就是message事件
        sb.append("retry:3000\n");
        sb.append("data:"+newMsg+"\n\n");
        return sb.toString();
    }

    private synchronized String getNewMessage() throws InterruptedException {
        //没有新数据之前 进入等待状态
        this.wait();
        return "this is a new msg，系统注册了新用户："+newUserId;
    }

    @RequestMapping(value = "/createNewMsg")
    public String createNewMsg(String userId) throws InterruptedException {
        System.out.println("createNewMsg ------");
        newUserId = UUID.randomUUID().toString();
        synchronized(this){
            //有了新数据  唤醒等待的线程
            this.notifyAll();
        }
        return "ok";
    }



}
