package com.dhy.ratelimit.resilience4j;

import com.dhy.ratelimit.resilience4j.common.IHelloService;
import feign.AsyncFeign;
import feign.Feign;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Title OpenFeignTest
 * @Description
 * @Author lvaolin
 * @Date 2021/2/28 21:52
 **/
@SpringBootTest
public class OpenFeignTest {

    /**
     * open feign同步调用测试
     */
    @Test
    void testHello() {

        IHelloService helloService = Feign
                .builder()
                .target(IHelloService.class, "http://localhost:8080/");
        Object dahuangya = helloService.hello("dahuangya");
        System.out.println(dahuangya);
    }


    /**
     * open feign异步调用测试
     */
    @Test
    void testAsyncHello() throws ExecutionException, InterruptedException {

        IHelloService helloService = AsyncFeign
                .asyncBuilder()
                .target(IHelloService.class, "http://localhost:8080/");
        CompletableFuture<String> completableFuture = helloService.asyncHello("dahuangya");
        System.out.println("1");
        completableFuture.thenRun(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("response 回来了");
            }
        });
        System.out.println("2");
        String s = completableFuture.get();
        System.out.println("3:"+s);

        for (;;);
    }

}
