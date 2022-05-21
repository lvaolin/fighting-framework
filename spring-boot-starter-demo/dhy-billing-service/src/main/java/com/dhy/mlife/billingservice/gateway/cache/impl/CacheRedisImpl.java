package com.dhy.mlife.billingservice.gateway.cache.impl;

import com.dhy.mlife.billingservice.gateway.cache.itf.CacheI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CacheRedisImpl implements CacheI {

    @Override
    public Long list$lpush(String orgId, String tag, String... value) {
        return null;
    }

    @Override
    public String list$lindex(String orgId, String tag, int index) {
        return null;
    }

    @Override
    public String list$rpop(String orgId, String tag) {
        return null;
    }

    @Override
    public List<String> list$lrange(String orgId, String tag, long start, long end) {
        return null;
    }

    @Override
    public long list$llen(String orgId, String tag) {
        return 0;
    }

    @Override
    public boolean hashmap$hmset(String orgId, String tag, Map<String, String> map) {
        return false;
    }

    @Override
    public boolean hashmap$hmset(String orgId, String tag, Map<String, String> map, int expire) {
        return false;
    }

    @Override
    public List<String> hashmap$hmget(String orgId, String tag, String... fileds) {
        return null;
    }

    @Override
    public boolean hashmap$hexists(String orgId, String tag, String filed) {
        return false;
    }

    @Override
    public boolean hashmap$del(String orgId, String tag) {
        return false;
    }

    @Override
    public Long hashmap$hdel(String orgId, String tag, String... fields) {
        return null;
    }

    @Override
    public Long hashmap$incrby(String orgId, String tag, String field, long value) {
        return null;
    }

    @Override
    public boolean string$set(String orgId, String tag, Object value) {
        return false;
    }

    @Override
    public boolean string$set(String orgId, String tag, Object value, int expire) {
        return false;
    }

    @Override
    public <T> T string$get(String orgId, String tag, Class<T> clazz) {
        return null;
    }

    @Override
    public String string$get(String orgId, String tag) {
        return null;
    }

    @Override
    public boolean delLikeKey(String orgId, String tag) {
        return false;
    }

    @Override
    public boolean delete(String orgId, String tag) {
        return false;
    }

    @Override
    public boolean exists(String orgId, String tag) {
        return false;
    }

    @Override
    public boolean expire(String orgId, String tag, int expire) {
        return false;
    }

    @Override
    public boolean expireForList(String orgId, String tag, int expire) {
        return false;
    }

    @Override
    public boolean expireForHashMap(String orgId, String tag, int expire) {
        return false;
    }

    @Override
    public boolean expireForSet(String orgId, String tag, int expire) {
        return false;
    }

    @Override
    public boolean expireForSortedSet(String orgId, String tag, int expire) {
        return false;
    }

    @Override
    public Long set$sadd(String orgId, String tag, String... strings) {
        return null;
    }

    @Override
    public Long set$srem(String orgId, String tag, String... strings) {
        return null;
    }

    @Override
    public Long sortedSet$zadd(String orgId, String tag, double score, String member) {
        return null;
    }

    @Override
    public Long sortedSet$zadd(String orgId, String tag, Map<String, Double> scoreMembers) {
        return null;
    }

    @Override
    public Long sortedSet$zrem(String orgId, String tag, String... member) {
        return null;
    }

    @Override
    public void object$write(String key, Object value, int expiredTime) {

    }

    @Override
    public void object$del(String key) {

    }

    @Override
    public <T> T object$get(String key) throws ClassNotFoundException {
        return null;
    }

    @Override
    public void object$remove(String key) {

    }

    @Override
    public boolean string$append(String orgId, String tag, String value, int expire) {
        return false;
    }

    @Override
    public boolean string$setString(String orgId, String tag, String value, int expire) {
        return false;
    }

    @Override
    public long getKeyTTL(String key) {
        return 0;
    }
}
