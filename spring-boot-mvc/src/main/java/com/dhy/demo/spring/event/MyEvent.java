package com.dhy.demo.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Title MyEvent
 * @Description
 * @Author lvaolin
 * @Date 2021/4/4 11:41
 **/
public class MyEvent extends ApplicationEvent {


    private String message;
    public MyEvent(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
