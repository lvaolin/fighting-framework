package com.dhy.springwebflux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Title SseController
 * @Description  sse 机制测试   server sent event
 * @Author lvaolin
 * @Date 2021/2/16 18:51
 **/
@RestController
@RequestMapping("/sse")
public class SseController {

    @RequestMapping(value = "/getTime",produces = "text/event-stream;charset=utf-8")
    public String subscribe(String userId){
        System.out.println("sse come in ------");
        LocalDateTime now = LocalDateTime.now();
        return now.toString()+" \n";
    }

}
