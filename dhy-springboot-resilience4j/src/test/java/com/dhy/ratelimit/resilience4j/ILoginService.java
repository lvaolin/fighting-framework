package com.dhy.ratelimit.resilience4j;

/**
 * @Project dhy-springboot-resilience4j
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/1/29 2:39 下午
 */
@FunctionalInterface
public interface ILoginService {
     boolean login();
}
