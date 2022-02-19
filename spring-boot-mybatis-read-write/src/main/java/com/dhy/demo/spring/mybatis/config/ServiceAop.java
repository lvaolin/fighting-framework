package com.dhy.demo.spring.mybatis.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Title ServiceAop
 * @Description
 * @Author lvaolin
 * @Date 2022/2/19 18:30
 **/
@Component
//@EnableAspectJAutoProxy(proxyTargetClass = false)
@Aspect
public class ServiceAop {

    @Resource(name = "dsReadOnlyKeyMapping")
    private Map<String,String> dsReadOnlyKeyMapping;

    @Pointcut("@annotation(DsReadonly)")
    public void pc(){

    }
    @Before("pc()")
    public void before(JoinPoint joinPoint){
        String readonlyStr = dsReadOnlyKeyMapping.get("biz-ds1");
        if (readonlyStr!=null) {
            String[] readonlys = readonlyStr.split(",");
            TraceUtil.setDbKey(readonlys[ThreadLocalRandom.current().nextInt(readonlys.length)]);
        }

    }
}