package com.dhy.mlife.billingservice.gateway.cache.itf;

import java.util.List;
import java.util.Map;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/11 上午9:15
 */
public interface CacheI {


    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param orgId
     * @param tag
     * @param value
     * @return 执行 LPUSH 命令后，列表的长度
     */
    Long list$lpush(String orgId, String tag, String... value);

    /**
     * 返回列表  中，下标为 index 的元素。
     *
     * @param orgId
     * @param tag
     * @param index
     * @return 列表中下标为 index 的元素。如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
     */
    String list$lindex(String orgId, String tag, int index);

    /**
     * 移除并返回列表  的尾元素。
     *
     * @param orgId
     * @param tag
     * @return 列表的尾元素。当 key 不存在时，返回 nil 。
     */
    String list$rpop(String orgId, String tag);

    /**
     * 返回指定区间的数据，从左到右，第一个元素的索引为0
     *
     * @param orgId
     * @param tag
     * @param start
     * @param end
     * @return
     */
    List<String> list$lrange(String orgId, String tag, long start, long end);

    /**
     * 返回指定队列的长度
     *
     * @param orgId
     * @param tag
     * @return
     */
    long list$llen(String orgId, String tag);


    /**
     * @param orgId
     * @param tag
     * @param map
     * @return
     */
    boolean hashmap$hmset(String orgId, String tag, Map<String, String> map);

    boolean hashmap$hmset(String orgId, String tag, Map<String, String> map, int expire);

    /**
     * @param orgId
     * @param tag
     * @param fileds
     * @return
     */
    List<String> hashmap$hmget(String orgId, String tag, String... fileds);


    /**
     * @param orgId
     * @param tag
     * @param filed
     * @return
     */
    boolean hashmap$hexists(String orgId, String tag, String filed);

    /**
     * @param orgId
     * @param tag
     * @return
     */
    boolean hashmap$del(String orgId, String tag);

    /**
     * 删除 hash 结构中指定的fields元素
     *
     * @param orgId
     * @param tag    orgId+tag 确定唯一的hash对象
     * @param fields 要删除的hash中元素的key 列表
     * @return 删除成功的元素个数
     */
    Long hashmap$hdel(String orgId, String tag, String... fields);

    /**
     * 累加，将指定 key 中执行 field 的值增加 value
     *
     * @param orgId
     * @param tag
     * @param field
     * @param value
     * @return
     */
    Long hashmap$incrby(String orgId, String tag, String field, long value);

    /**
     * 设置缓存 ， 有效期为永远
     *
     * @param orgId
     * @param tag
     * @param value
     * @return
     */
    boolean string$set(String orgId, String tag, Object value);

    /**
     * 设置带有效期的缓存，单位（秒）
     *
     * @param orgId
     * @param tag
     * @param value
     * @param expire
     * @return
     */
    boolean string$set(String orgId, String tag, Object value, int expire);


    /**
     * 返回 key 所关联的对象，指定 clazz 将自动将json字符串转换为对象返回。
     *
     * @param orgId
     * @param tag
     * @param clazz json字符串的原始类型
     * @return 转换后的对象
     */
    <T> T string$get(String orgId, String tag, Class<T> clazz);

    /**
     * 返回 key 所关联的字符串值。如果 key 不存在那么返回特殊值 nil 。假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     *
     * @param orgId
     * @param tag
     * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。
     */
    String string$get(String orgId, String tag);


    boolean delLikeKey(String orgId, String tag);


    /**
     * 删除key对应的cache
     *
     * @param orgId
     * @param tag
     * @return
     */
    boolean delete(String orgId, String tag);

    /**
     * key是否存在
     *
     * @param orgId
     * @param tag
     * @return
     */
    boolean exists(String orgId, String tag);

    /**
     * 重新设置缓存有效期
     *
     * @param orgId
     * @param tag
     * @param expire
     * @return
     */
    boolean expire(String orgId, String tag, int expire);

    /**
     * 重新设置缓存有效期
     *
     * @param orgId
     * @param tag
     * @param expire
     * @return
     */
    boolean expireForList(String orgId, String tag, int expire);

    /**
     * 重新设置缓存有效期
     *
     * @param orgId
     * @param tag
     * @param expire
     * @return
     */
    boolean expireForHashMap(String orgId, String tag, int expire);

    /**
     * 重新设置缓存有效期
     *
     * @param orgId
     * @param tag
     * @param expire
     * @return
     */
    boolean expireForSet(String orgId, String tag, int expire);

    /**
     * 重新设置缓存有效期
     *
     * @param orgId
     * @param tag
     * @param expire
     * @return
     */
    boolean expireForSortedSet(String orgId, String tag, int expire);


    /**
     * 向set集合中增加一个元素
     *
     * @param orgId
     * @param tag
     * @param strings
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
     */
    Long set$sadd(String orgId, String tag, String... strings);

    /**
     * 移除set集合中的一个或多个元素，不存在的元素会被忽略
     *
     * @param orgId
     * @param tag
     * @param strings
     * @return 被成功移除的元素的数量，不包括被忽略的元素
     */
    Long set$srem(String orgId, String tag, String... strings);

    /**
     * 将一个member 元素及其 score 值加入到有序集当中
     *
     * @param orgId
     * @param tag
     * @param score
     * @param member
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    Long sortedSet$zadd(String orgId, String tag, double score, String member);

    /**
     * 将多个member 元素及其 score 值加入到有序集当中
     *
     * @param orgId
     * @param tag
     * @param scoreMembers
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    Long sortedSet$zadd(final String orgId, final String tag, final Map<String, Double> scoreMembers);


    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * @param orgId
     * @param tag
     * @param member
     * @return 被成功移除的成员的数量，不包括被忽略的成员。
     */
    Long sortedSet$zrem(final String orgId, final String tag, String... member);

    void object$write(String key, Object value, int expiredTime);

    void object$del(String key);

    <T> T object$get(String key) throws ClassNotFoundException;

    void object$remove(String key);

    boolean string$append(String orgId, String tag, String value, int expire);

    boolean string$setString(String orgId, String tag, String value, int expire);

    long getKeyTTL(String key);


}
