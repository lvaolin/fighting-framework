package com.dhy.demo.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Title MyEventPublisher
 * @Description
 * @Author lvaolin
 * @Date 2021/4/4 11:44
 **/
@Component
public class MyPublisher {
    @Autowired
    ApplicationContext applicationContext;

    public void publish(String message){
        //发布事件
        applicationContext.publishEvent(new MyEvent(this, message));
    }
}
