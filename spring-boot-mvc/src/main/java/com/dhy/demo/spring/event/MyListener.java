package com.dhy.demo.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Title MyListener
 * @Description
 * @Author lvaolin
 * @Date 2021/4/4 11:42
 **/
@Component
public class MyListener  implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("监听到事件发生："+myEvent.getMessage());
    }
}
