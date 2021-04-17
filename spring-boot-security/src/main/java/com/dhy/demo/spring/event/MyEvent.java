package com.dhy.demo.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Title MyEvent
 * @Description
 * @Author lvaolin
 * @Date 2021/4/4 11:41
 **/
public class MyEvent extends ApplicationEvent {


    private MyMsg message;
    public MyEvent(Object source,MyMsg message) {
        super(source);
        this.message = message;
    }

    public MyMsg getMessage() {
        return message;
    }

    public void setMessage(MyMsg message) {
        this.message = message;
    }
}
