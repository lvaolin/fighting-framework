package com.dhy.spring.controller.sse;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title SseController
 * @Description  SseEmitter(Spring MVC 对sse的支持) 机制测试
 * @Author lvaolin
 * @Date 2021/2/16 18:51
 **/
@RestController
@RequestMapping("/sse")
public class SseSpringMvcSseEmitterController {

    //存放 各个用户的 SseEmitter
    Map<String,SseEmitter> emitterMap = new ConcurrentHashMap<String,SseEmitter>();


    //接入一个用户
    @RequestMapping(value = "/start",produces = "text/event-stream;charset=utf-8")
    public SseEmitter start(@NonNull String clientId){
        System.out.println("start--"+clientId+"----");
        SseEmitter emitter = new SseEmitter(0L);
        emitterMap.put(clientId,emitter);
        //更新在线列表
        onlineUserUpdate();
        return emitter;
    }

    //向指定用户发送消息
    @RequestMapping(value = "/send")
    public String send(@NonNull String clientId,@NonNull String content,@NonNull String fromUserId){
        String result = null;
        System.out.println("send--"+clientId+"----"+content);
        SseEmitter emitter = emitterMap.get(clientId);
        try {
            if (emitter!=null) {
                emitter.send(fromUserId+"对你说:"+content);
                result = "success";
            }else {
                result = "fail";
            }

        } catch (IOException e) {
            e.printStackTrace();
            emitterMap.remove(clientId);
        }
        return result;
    }

    //群发消息
    @RequestMapping(value = "/sendAll")
    public String sendAll(@NonNull String content,@NonNull String fromUserId){
        System.out.println("sendAll------"+content);

        emitterMap.forEach((key,emitter)->{
            try {
                emitter.send(fromUserId+"对大家说："+content);
            } catch (IOException e) {
                e.printStackTrace();
                emitterMap.remove(key);
            }
        });
//        emitterMap.values().parallelStream().forEach((emitter)->{
//            try {
//                emitter.send(fromUserId+"对大家说："+content);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        return "ok";
    }

    //广播在线用户列表
    public String onlineUserUpdate(){
        System.out.println("onlineUserUpdate------");

        String onlineUsers = String.join(",", emitterMap.keySet());
        emitterMap.values().parallelStream().forEach((emitter)->{
            try {
                emitter.send("onlineUsers:"+onlineUsers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return "ok";
    }


    //终止一个用户的连接
    @RequestMapping(value = "/stop")
    public String stop(@NonNull String clientId){
        String result = null;
        System.out.println("end------"+clientId);
        SseEmitter emitter = emitterMap.get(clientId);

        try {
            if (emitter!=null) {
                emitterMap.remove(clientId);
                //更新在线列表
                onlineUserUpdate();
                result = "success";
            }else {
                result = "fail";
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = "fail";
        }
        return result;
    }

}
