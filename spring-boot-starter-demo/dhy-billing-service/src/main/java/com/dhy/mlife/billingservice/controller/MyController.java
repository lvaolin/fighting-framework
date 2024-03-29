package com.dhy.mlife.billingservice.controller;

import com.dhy.mlife.Spring;
import com.dhy.mlife.billingservice.dto.BillInfoFormNew;
import com.dhy.mlife.billingservice.dto.MyResponseDataXML;
import com.dhy.mlife.billingservice.service.itf.MyServiceI;
import com.dhy.mlife.common.configure.config.MlifeConfig;
import com.dhy.mlife.common.context.AppContext;
import com.dhy.mlife.common.context.AppContextHolder;
import com.dhy.mlife.common.core.BusinessException;
import com.dhy.mlife.common.core.MyResponseData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;

/**
 * 公共行为在拦截器处理，这里只处理具体业务逻辑
 */
@RestController
@Slf4j
public class MyController {

    @Autowired
    private MlifeConfig mlifeConfig;

    @Autowired
    private MyServiceI myService;

    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object test(@RequestBody BillInfoFormNew actionForm, HttpServletRequest request, HttpServletResponse response) throws BusinessException {

        //获取公共报文
        AppContext appContext = AppContextHolder.get();
        String transactionSessionId = appContext.getTransactionSessionId();

        log.info("公共报文：" + appContext.toString());

        //业务参数
        log.info("业务参数:" + actionForm.toString());

        //业务处理
        HashMap<String, String> map = new HashMap<>();
        map.put("result", "true");
        map.put("logId", UUID.randomUUID().toString());
        map.put("costTime", "100毫秒");

        myService.bizMethod();

        //throw new RuntimeException("异常测试");
        //业务结果
        return new MyResponseData(map);
    }


    @PostMapping(value = "/testxml", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Object testxml(@RequestBody BillInfoFormNew actionForm, HttpServletRequest request, HttpServletResponse response) throws BusinessException {

        log.info(mlifeConfig.getServiceName());
        //请求上下文获取demo（公共报文）
        AppContext appContext = AppContextHolder.get();
        log.info("公共报文：" + appContext.toString());

        //业务参数
        log.info("业务参数:" + actionForm.toString());

        //业务处理
        HashMap<String, String> map = new HashMap<>();
        map.put("result", "true");
        map.put("logId", UUID.randomUUID().toString());
        map.put("costTime", "100毫秒");
        return new MyResponseDataXML(map);
    }

    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get(@RequestParam long id, HttpServletRequest request, HttpServletResponse response) throws BusinessException {

        //这个测试是想说明， ResponseBody 这个注解会默认将对象序列化成json字符串，
        // 如果你return的是字符串而不是对象，它就不会做额外处理，直接响应写出即可
        //而 produces仅仅是声明你响应的字符串的格式，利于客户端的显示处理，但是你响应的真实数据完全可以不是json格式，那就是你误导了客户端而已
        String jsonstring = "{\"name\":\"aaa\"}";

        return "jsonstring";

        //return new MyResponseData(myService.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        return new MyResponseData(myService.selectAll());
    }

    @RequestMapping(value = "/setCache", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object setCache(KV kv, HttpServletRequest request, HttpServletResponse response) throws BusinessException {
//        myService.setCache(kv.getKey(), kv.getValue());
//        return new MyResponseData("okl");

        try {
            throw new RuntimeException("异常测试");
        }catch (Exception e){
            throw e;
        }finally {
            return new MyResponseData("okl");
        }
    }

    @Data
    class KV {
        private String key;
        private String value;
    }


    @RequestMapping(value = "/classloadertest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object classloadertest(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        return Spring.classfrom;
    }

}
