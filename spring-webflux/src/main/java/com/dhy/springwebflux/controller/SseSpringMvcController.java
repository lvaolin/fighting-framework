package com.dhy.springwebflux.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title SseController
 * @Description  sse 机制测试   server sent event
 * @Author lvaolin
 * @Date 2021/2/16 18:51
 **/
@RestController
@RequestMapping("/sse")
public class SseSpringMvcController {

    Map<String,SseEmitter> emitterMap = new ConcurrentHashMap<String,SseEmitter>();
    @RequestMapping(value = "/start")
    public SseEmitter start(@NonNull String clientId){
        System.out.println("start--"+clientId+"----");
        SseEmitter emitter = new SseEmitter();
        emitterMap.put(clientId,emitter);
        return emitter;
    }

    @RequestMapping(value = "/send")
    public String send(@NonNull String clientId,@NonNull String content){
        String result = null;
        System.out.println("send--"+clientId+"----"+content);
        SseEmitter emitter = emitterMap.get(clientId);
        try {
            if (emitter!=null) {
                emitter.send(content);
                result = "success";
            }else {
                result = "fail";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/sendAll")
    public String sendAll(@NonNull String content){
        System.out.println("sendAll------"+content);

        emitterMap.values().parallelStream().forEach((emitter)->{
            try {
                emitter.send(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return "ok";
    }

    @RequestMapping(value = "/end")
    public String end(@NonNull String clientId){
        String result = null;
        System.out.println("end------"+clientId);
        SseEmitter emitter = emitterMap.get(clientId);

        try {
            if (emitter!=null) {
                emitter.complete();
                result = "success";
            }else {
                result = "fail";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
