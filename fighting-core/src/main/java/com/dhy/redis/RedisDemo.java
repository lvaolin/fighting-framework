package com.dhy.redis;

import java.io.IOException;

public class RedisDemo {

    public static void main(String[] args) throws IOException {
        RedisClient redisClient = new RedisClient("127.0.0.1",6379);
        redisClient.set("name","lvaolin--中国人");
        redisClient.get("name");
    }
}
