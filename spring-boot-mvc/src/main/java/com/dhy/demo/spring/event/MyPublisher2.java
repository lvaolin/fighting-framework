package com.dhy.demo.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Title MyEventPublisher
 * 通过 ApplicationPublisherAware接口实现具备发布事件的能力
 * @Description
 * @Author lvaolin
 * @Date 2021/4/4 11:44
 **/
@Component
public class MyPublisher2 {
    @Autowired
    ApplicationEventPublisher publishEvent;

    public void publish(String message){
        //发布事件
        publishEvent.publishEvent(new MyEvent(this, message+" from ApplicationEventPublisher"));
    }
}
