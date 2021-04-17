package com.dhy.demo.spring.event;

import com.alibaba.fastjson.JSON;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Title MyListener2  通过方法加指定注解
 * 来监听事件
 * @Description
 * @Author lvaolin
 * @Date 2021/4/5 9:36
 **/
@Component
public class MyListener2 {

    @EventListener
    public void onApplicationEvent2(MyEvent myEvent) {
        System.out.println("2号监听者监听1号事件发生："+ JSON.toJSONString(myEvent.getMessage()));
    }
}
