package com.dhy.demo.spring.event;

import com.alibaba.fastjson.JSON;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Title MyListener3  基于条件的监听过滤
 * 来监听事件 ，类似 在同一个队列中根据 消息tag过滤消息
 * @Description
 * @Author lvaolin
 * @Date 2021/4/5 9:36
 **/
@Component
public class MyListener3 {
    //异步执行这个消息，使用默认线程池---
    @Async("myTaskAsyncPool")
    @EventListener(condition = "#myEvent.message.id == 1")
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("3号监听者监听id=1的MyEvent事件："+ JSON.toJSONString(myEvent.getMessage()));
    }
}
