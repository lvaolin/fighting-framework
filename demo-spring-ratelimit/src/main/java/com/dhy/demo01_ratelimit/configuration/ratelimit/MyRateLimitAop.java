package com.dhy.demo01_ratelimit.configuration.ratelimit;


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

    @Before(value="com.dhy.demo01_ratelimit.configuration.ratelimit.MyRateLimitAop.pointcut3()")
    public void before(JoinPoint jp){
        System.out.println("before-----------");
    }

    @After(value="com.dhy.demo01_ratelimit.configuration.ratelimit.MyRateLimitAop.pointcut1()")
    public void after(JoinPoint jp){
        System.out.println("after-----------");
    }
    @AfterReturning(value = "com.dhy.demo01_ratelimit.configuration.ratelimit.MyRateLimitAop.pointcut1()",returning = "result")
    public void afterReturn(JoinPoint jp, Object result){
        System.out.println("after returning-----------");
    }

    @AfterThrowing(value = "com.dhy.demo01_ratelimit.configuration.ratelimit.MyRateLimitAop.pointcut1()",throwing = "e")
    public void handleException(JoinPoint jp,Exception e){
        System.out.println("exception-----------");
    }
    @Around(value = "com.dhy.demo01_ratelimit.configuration.ratelimit.MyRateLimitAop.pointcut2(user)")
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

    @Around(value = "com.dhy.demo01_ratelimit.configuration.ratelimit.MyRateLimitAop.pointcut3()")
    public Object method(ProceedingJoinPoint jp){
        try {
            System.out.println("around------限流判断-----");
            String methodName = jp.getSignature().getName();
            if(!RateLimitForLouTong.acquirePermit(methodName)){
                System.out.println("around------限流未通过-----");
                throw new RuntimeException("限流未通过");
            }
            System.out.println("around------进漏桶-----");
            Object result = jp.proceed(jp.getArgs());
            RateLimitForLouTong.releasePermit(methodName);
            System.out.println("around------出漏桶-----");
            return result;
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            return null;
        }
    }

}
