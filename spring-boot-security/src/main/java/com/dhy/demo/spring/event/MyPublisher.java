package com.dhy.demo.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

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
        MyMsg myMsg = new MyMsg();
        myMsg.setName(message);
        myMsg.setId(1L);
        myMsg.setAge(18);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("history");
        strings.add("math");
        myMsg.setTags(strings);
        //发布事件
        applicationContext.publishEvent(new MyEvent(this, myMsg));
    }
}
