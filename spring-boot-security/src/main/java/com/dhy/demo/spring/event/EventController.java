package com.dhy.demo.spring.event;

import com.dhy.demo.spring.event.MyPublisher;
import com.dhy.demo.spring.event.MyPublisher2;
import com.dhy.demo.spring.event.MyPublisher3;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Title EventController
 * @Description
 * @Author lvaolin
 * @Date 2021/4/4 11:51
 **/
@RestController
@RequestMapping("/spring/event")
public class EventController {

    @Resource
    private MyPublisher myPublisher;
    @Resource
    private MyPublisher2 myPublisher2;
    @Resource
    private MyPublisher3 myPublisher3;
    @RequestMapping("/create")
    public String createEvent(){
        myPublisher.publish("myevent1 来了");
        myPublisher2.publish("myevent2 来了");
        myPublisher3.publish("myevent3 来了");
        return "事件发布成功";
    }
}
