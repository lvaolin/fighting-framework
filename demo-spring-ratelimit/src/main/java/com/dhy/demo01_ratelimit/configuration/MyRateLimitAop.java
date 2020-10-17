package com.dhy.demo01_ratelimit.configuration;


import com.dhy.demo01_ratelimit.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyRateLimitAop {

    //切入点的注解
    @Pointcut(value="execution(* com.dhy.demo01_ratelimit.service.OpenApiServiceImpl.createUser(..))")
    private void pointcut1(){}

    @Pointcut(value="args(user)", argNames = "user")
    private void pointcut2(User user){}

    @Pointcut(value="@annotation(com.dhy.demo01_ratelimit.annotation.MyRateLimit)")
    private void pointcut3(){}

    @Pointcut(value="execution(* com.dhy.demo01_ratelimit.service.OpenApiServiceImpl.createUser(..))")
    private void pointcut4(){}

    @Before(value="MyRateLimitAop.pointcut3()")
    public void before(JoinPoint jp){
        System.out.println("before-----------");
    }

    @After(value="MyRateLimitAop.pointcut1()")
    public void after(JoinPoint jp){
        System.out.println("after-----------");
    }
    @AfterReturning(value = "MyRateLimitAop.pointcut1()",returning = "result")
    public void afterReturn(JoinPoint jp, Object result){
        System.out.println("after returning-----------");
    }

    @AfterThrowing(value = "MyRateLimitAop.pointcut1()",throwing = "e")
    public void handleException(JoinPoint jp,Exception e){
        System.out.println("exception-----------");
    }
    @Around(value = "MyRateLimitAop.pointcut2(user)")
    public Object method(ProceedingJoinPoint jp, User user){
        try {
            System.out.println("around------1-----");
            Object result = jp.proceed(jp.getArgs());
            System.out.println("around------2-----");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
