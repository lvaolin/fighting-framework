package com.dhy.ratelimit.resilience4j.common;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Title IHelloService
 * @Description
 * @Author lvaolin
 * @Date 2021/2/28 21:38
 **/
public interface IHelloService {
    @RequestLine("GET /hello?name={name}")
    String hello(@Param("name") String name);

    @RequestLine("GET /asyncHello?name={name}")
    CompletableFuture<String> asyncHello(@Param("name") String name);
}
