package com.dhy.demo.spring.controller;

import com.dhy.demo.spring.event.MyPublisher;
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
    @RequestMapping("/create")
    public String createEvent(){
        myPublisher.publish("myevent 来了");
        return "事件发布成功";
    }
}
