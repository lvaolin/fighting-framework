package com.dhy.mlife.billingservice.controller;

import com.dhy.mlife.billingservice.dto.BillInfoFormNew;
import com.dhy.mlife.billingservice.dto.MyResponseDataXML;
import com.dhy.mlife.common.configure.config.MlifeConfig;
import com.dhy.mlife.common.context.AppContext;
import com.dhy.mlife.common.context.AppContextHolder;
import com.dhy.mlife.common.core.BusinessException;
import com.dhy.mlife.common.core.MyResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.UUID;

/**
 * 公共行为在拦截器处理，这里只处理具体业务逻辑
 */
@RestController
@Slf4j
public class MyController  {

    @Autowired
    private MlifeConfig mlifeConfig;

    @PostMapping(value = "/test",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object test(@RequestBody BillInfoFormNew actionForm, HttpServletRequest request, HttpServletResponse response) throws BusinessException {

        //获取公共报文
        AppContext appContext = AppContextHolder.get();
        String transactionSessionId = appContext.getTransactionSessionId();

        log.info("公共报文："+appContext.toString());

        //业务参数
        log.info("业务参数:"+actionForm.toString());

        //业务处理
        HashMap<String, String> map = new HashMap<>();
        map.put("result","true");
        map.put("logId", UUID.randomUUID().toString());
        map.put("costTime", "100毫秒");
        //throw new RuntimeException("异常测试");
        //业务结果
        //response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return new MyResponseData(map);
    }

    @PostMapping(value = "/testxml",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_XML_VALUE)
    public Object testxml(@RequestBody BillInfoFormNew actionForm, HttpServletRequest request, HttpServletResponse response) throws BusinessException {

        log.info(mlifeConfig.getServiceName());
        //请求上下文获取demo（公共报文）
        AppContext appContext = AppContextHolder.get();
        log.info("公共报文："+appContext.toString());

        //业务参数
        log.info("业务参数:"+actionForm.toString());

        //业务处理
        HashMap<String, String> map = new HashMap<>();
        map.put("result","true");
        map.put("logId", UUID.randomUUID().toString());
        map.put("costTime", "100毫秒");
        return new MyResponseDataXML(map);
    }



}
