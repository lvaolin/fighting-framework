package com.dhy.ratelimit.resilience4j.controller;

import com.dhy.ratelimit.resilience4j.domain.user.UserEntity;
import com.dhy.ratelimit.resilience4j.util.CircuitBreakerUtil;
import com.dhy.ratelimit.resilience4j.util.MyResponseData;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
@ResponseBody
public class HelloController  {
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    //熔断器参数配置
    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .slidingWindowSize(10)
            .slidingWindowType(CircuitBreakerConfig.DEFAULT_SLIDING_WINDOW_TYPE)
            .minimumNumberOfCalls(10)
            .permittedNumberOfCallsInHalfOpenState(4)
            .waitDurationInOpenState(Duration.ofSeconds(10))
            .build();

    @RequestMapping("/circuit-breaker")
    public MyResponseData circuitBeaker(@RequestParam String name) throws Exception {

        try {
            //注册一个熔断器，不存在则新增，存在则复用
            CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("circuit-breaker",config);
            //熔断器事件监听
            //CircuitBreakerUtil.addCircuitBreakerListener(circuitBreaker);
            //当前熔断器的状态数据
            CircuitBreakerUtil.getCircuitBreakerStatus(System.currentTimeMillis()+"",circuitBreaker);
            Callable<String> stringCallable = circuitBreaker.decorateCallable(()->{
                UserEntity userEntity = new UserEntity(1L,name);
                return userEntity.say();
            });
            String bizdata = stringCallable.call();
            return new MyResponseData(true,bizdata);
        }catch (Throwable t){
            return new MyResponseData(false,t.getMessage());
        }

    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        System.out.println(name);
        return "hello"+name;
    }
    @RequestMapping("/asyncHello")
    public String asyncHello(@RequestParam String name) {
        System.out.println(name);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "async hello"+name;
    }
}
