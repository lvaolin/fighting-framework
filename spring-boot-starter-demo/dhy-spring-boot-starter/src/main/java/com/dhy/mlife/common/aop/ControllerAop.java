package com.dhy.mlife.common.aop;

import com.dhy.mlife.common.context.AppContext;
import com.dhy.mlife.common.context.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;

@Aspect
@Slf4j
public class ControllerAop {

    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
                    "@annotation(org.springframework.web.bind.annotation.PostMapping)"
    )
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        log.info("----参数注入-----");
        //参数注入
        //------兼容旧模式，解决下工作量问题------begin---
        AppContext appContext = AppContextHolder.get();

        for (Object arg : args) {
            if (AppContext.class.isAssignableFrom(arg.getClass())) {
                AppContext bizForm = (AppContext) arg;
                BeanUtils.copyProperties(appContext, bizForm);
            }
        }

        //------兼容旧模式，解决下工作量问题------end---

        return point.proceed(args);
    }
}
