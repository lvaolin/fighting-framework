package com.dhy.demo.spring.redis.service;

/**
 * @Project parent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/6/11 5:37 下午
 */
public interface ICacheService {
    Object setCache1(String name);
    Object setCache2(String name);
    Object updateCache1(String name);
    Object deleteCache1(String name);
}
