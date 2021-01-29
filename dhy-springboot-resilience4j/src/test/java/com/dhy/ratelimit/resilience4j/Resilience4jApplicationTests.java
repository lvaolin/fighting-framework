package com.dhy.ratelimit.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.runtime.Log;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@SpringBootTest
class Resilience4jApplicationTests {

    @Test
    void contextLoads() {

        System.out.println("resilience4j test");
    }


    @Test
    void testRateLimiter() {
        LoginService loginService = new LoginService();
        // 配置信息
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(100))
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(1)
                .build();
        // 限流器
        RateLimiter rateLimiter = RateLimiter.of("backendName", config);


        while (true) {
            //指定限流方法
            Supplier<Boolean> restrictedSupplier = RateLimiter
                    .decorateSupplier(rateLimiter, loginService::login);

            IntStream.rangeClosed(1, 5)
                    .parallel()
                    .forEach(i -> {
                        Try<Boolean> aTry = Try.ofSupplier(restrictedSupplier);
                        System.out.println(aTry.isSuccess());
                    });
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testCircuitBreaker() {
        // Create a CircuitBreaker (use default configuration)
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
                .custom()
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker
                .of("backendName", circuitBreakerConfig);
        while (true) {
            LoginService loginService = new LoginService();
            Boolean result = circuitBreaker.executeSupplier(() -> {
                System.out.println("----");
                return true;
            });
            System.out.println(result);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 超时限流器
     */
    @Test
    public void testTimelimiter() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(600))
                .cancelRunningFuture(true)
                .build();
        TimeLimiter timeLimiter = TimeLimiter.of(config);

        while (true) {
            LoginService loginService = new LoginService();

            Supplier<Future<Boolean>> futureSupplier = () -> {
                return executorService.submit(loginService::login);
            };
            Callable<Boolean> restrictedCall = TimeLimiter.decorateFutureSupplier(timeLimiter, futureSupplier);
            Try.of(restrictedCall::call)
                    .onFailure(throwable -> System.out.println("We might have timed out or the circuit breaker has opened."));

        }
    }

    @Test
    public void testRetry(){
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
        // Create a Retry with at most 3 retries and a fixed time interval between retries of 500ms
        Retry retry = Retry.ofDefaults("backendName");

        while (true) {
            LoginService loginService = new LoginService();
            // Decorate your call to BackendService.doSomething() with a CircuitBreaker
            Supplier<Boolean> decoratedSupplier = CircuitBreaker
                    .decorateSupplier(circuitBreaker, loginService::login);

            // Decorate your call with automatic retry
            decoratedSupplier = Retry
                    .decorateSupplier(retry, decoratedSupplier);

            // Execute the decorated supplier and recover from any exception
            Boolean result = Try.ofSupplier(decoratedSupplier)
                    .recover(throwable -> true).get();
            System.out.println(result);
        }

    }

}
