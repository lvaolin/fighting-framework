package com.dhy.mlife.billingservice.gateway.cache.impl;

import com.alibaba.fastjson.JSON;
import com.dhy.mlife.billingservice.gateway.cache.itf.CacheI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Primary
@Slf4j
public class CacheRedisImpl implements CacheI {
    private final String LOCK_SUCCESS = "OK";
    private final String SET_IF_NOT_EXIST = "NX";
    private final String SET_WITH_EXPIRE_TIME_PX = "PX";
    private final String SET_WITH_EXPIRE_TIME_EX = "EX";
    private final int EXPIRE_TIME = 30 * 60;
    private JedisPool pool = null;
    @Autowired
    private RedisConfig redisConfig;

    @PostConstruct
    public void init() {
        this.pool = redisConfig.getJedisPool();
    }


    public Jedis getJedis() {
        return pool != null ? pool.getResource() : null;
    }

    public Boolean isAvailable() {
        return pool != null;
    }

    /**
     * 统一管理key生成规则
     *
     * @param sessionId
     * @param tag
     * @return
     */
    private String getKey(String sessionId, String tag) {
        return "string-" + sessionId + "-" + tag;
    }

    private String getListKey(String sessionId, String tag) {
        return "list-" + sessionId + "-" + tag;
    }

    private String getHashMapKey(String sessionId, String tag) {
        return "hashmap-" + sessionId + "-" + tag;
    }

    private String getSetKey(String sessionId, String tag) {
        return "set-" + sessionId + "-" + tag;
    }

    private String getSortedSetKey(String sessionId, String tag) {
        return "sortedset-" + sessionId + "-" + tag;
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param sessionId
     * @param tag
     * @param value
     * @return 执行 LPUSH 命令后，列表的长度
     */
    public Long list$lpush(String sessionId, String tag, String... value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(getListKey(sessionId, tag), value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    }

    /**
     * 返回列表  中，下标为 index 的元素。
     *
     * @param sessionId
     * @param tag
     * @param index
     * @return 列表中下标为 index 的元素。如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
     */
    public String list$lindex(String sessionId, String tag, int index) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lindex(getListKey(sessionId, tag), index);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 移除并返回列表  的尾元素。
     *
     * @param sessionId
     * @param tag
     * @return 列表的尾元素。当 key 不存在时，返回 nil 。
     */
    public String list$rpop(String sessionId, String tag) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.rpop(getListKey(sessionId, tag));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回指定区间的数据，从左到右，第一个元素的索引为0
     *
     * @param sessionId
     * @param tag
     * @param start
     * @param end
     * @return
     */
    public List<String> list$lrange(String sessionId, String tag, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lrange(getListKey(sessionId, tag), start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回指定队列的长度
     *
     * @param sessionId
     * @param tag
     * @return
     */
    public long list$llen(String sessionId, String tag) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.llen(getListKey(sessionId, tag));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * @param sessionId
     * @param tag
     * @param map
     * @return
     */
    public boolean hashmap$hmset(String sessionId, String tag, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hmset(getHashMapKey(sessionId, tag), map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean hashmap$hmset(String sessionId, String tag, Map<String, String> map, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hmset(getHashMapKey(sessionId, tag), map);
            jedis.expire(getHashMapKey(sessionId, tag), expire);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param sessionId
     * @param tag
     * @param fileds
     * @return
     */
    public List<String> hashmap$hmget(String sessionId, String tag, String... fileds) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hmget(getHashMapKey(sessionId, tag), fileds);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * @param sessionId
     * @param tag
     * @param filed
     * @return
     */
    public boolean hashmap$hexists(String sessionId, String tag, String filed) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hexists(getHashMapKey(sessionId, tag), filed);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param sessionId
     * @param tag
     * @return
     */
    public boolean hashmap$del(String sessionId, String tag) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(getHashMapKey(sessionId, tag));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 删除 hash 结构中指定的fields元素
     *
     * @param sessionId
     * @param tag       sessionId+tag 确定唯一的hash对象
     * @param fields    要删除的hash中元素的key 列表
     * @return 删除成功的元素个数
     */
    public Long hashmap$hdel(String sessionId, String tag, String... fields) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hdel(getHashMapKey(sessionId, tag), fields);
        } catch (Exception e) {
            throw new RuntimeException("redis异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 累加，将指定 key 中执行 field 的值增加 value
     *
     * @param sessionId
     * @param tag
     * @param field
     * @param value
     * @return
     */
    public Long hashmap$incrby(String sessionId, String tag, String field, long value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hincrBy(getHashMapKey(sessionId, tag), field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 设置缓存 ， 有效期为永远
     *
     * @param sessionId
     * @param tag
     * @param value
     * @return
     */
    public boolean string$set(String sessionId, String tag, Object value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (value != null) {
                jedis.set(getKey(sessionId, tag), JSON.toJSONString(value));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置带有效期的缓存，单位（秒）
     *
     * @param sessionId
     * @param tag
     * @param value
     * @param expire
     * @return
     */
    public boolean string$set(String sessionId, String tag, Object value, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (value != null) {
                jedis.set(getKey(sessionId, tag), JSON.toJSONString(value));
                jedis.expire(getKey(sessionId, tag), expire);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 返回 key 所关联的对象，指定 clazz 将自动将json字符串转换为对象返回。
     *
     * @param sessionId
     * @param tag
     * @param clazz     json字符串的原始类型
     * @return 转换后的对象
     */
    public <T> T string$get(String sessionId, String tag, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String temp = jedis.get(getKey(sessionId, tag));
            if (temp != null) {
                return JSON.parseObject(temp, clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 返回 key 所关联的字符串值。如果 key 不存在那么返回特殊值 nil 。假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     *
     * @param sessionId
     * @param tag
     * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。
     */
    public String string$get(String sessionId, String tag) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(getKey(sessionId, tag));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public boolean delLikeKey(String sessionId, String tag) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Set<String> set = jedis.keys(getKey(sessionId, tag));
            for (String key1 : set) {
                jedis.del(key1);
            }
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }


    /**
     * 删除key对应的cache
     *
     * @param sessionId
     * @param tag
     * @return
     */
    public boolean delete(String sessionId, String tag) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(getKey(sessionId, tag));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * key是否存在
     *
     * @param sessionId
     * @param tag
     * @return
     */
    public boolean exists(String sessionId, String tag) {
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            flag = jedis.exists(getKey(sessionId, tag));
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return flag;

    }

    /**
     * 重新设置缓存有效期
     *
     * @param sessionId
     * @param tag
     * @param expire
     * @return
     */
    public boolean expire(String sessionId, String tag, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long i = jedis.expire(getKey(sessionId, tag), expire);
            return i != 0 && i != 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 重新设置缓存有效期
     *
     * @param sessionId
     * @param tag
     * @param expire
     * @return
     */
    public boolean expireForList(String sessionId, String tag, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long i = jedis.expire(getListKey(sessionId, tag), expire);
            return i != 0 && i != 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 重新设置缓存有效期
     *
     * @param sessionId
     * @param tag
     * @param expire
     * @return
     */
    public boolean expireForHashMap(String sessionId, String tag, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long i = jedis.expire(getHashMapKey(sessionId, tag), expire);
            return i != 0 && i != 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 重新设置缓存有效期
     *
     * @param sessionId
     * @param tag
     * @param expire
     * @return
     */
    public boolean expireForSet(String sessionId, String tag, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long i = jedis.expire(getSetKey(sessionId, tag), expire);
            return i != 0 && i != 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 重新设置缓存有效期
     *
     * @param sessionId
     * @param tag
     * @param expire
     * @return
     */
    public boolean expireForSortedSet(String sessionId, String tag, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long i = jedis.expire(getSortedSetKey(sessionId, tag), expire);
            return i != 0 && i != 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 向set集合中增加一个元素
     *
     * @param sessionId
     * @param tag
     * @param strings
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
     */
    public Long set$sadd(String sessionId, String tag, String... strings) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(getSetKey(sessionId, tag), strings);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    }

    /**
     * 移除set集合中的一个或多个元素，不存在的元素会被忽略
     *
     * @param sessionId
     * @param tag
     * @param strings
     * @return 被成功移除的元素的数量，不包括被忽略的元素
     */
    public Long set$srem(String sessionId, String tag, String... strings) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(getSetKey(sessionId, tag), strings);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    }

    /**
     * 将一个member 元素及其 score 值加入到有序集当中
     *
     * @param sessionId
     * @param tag
     * @param score
     * @param member
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long sortedSet$zadd(String sessionId, String tag, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zadd(getSortedSetKey(sessionId, tag), score, member);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    }

    /**
     * 将多个member 元素及其 score 值加入到有序集当中
     *
     * @param sessionId
     * @param tag
     * @param scoreMembers
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long sortedSet$zadd(final String sessionId, final String tag, final Map<String, Double> scoreMembers) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zadd(getSortedSetKey(sessionId, tag), scoreMembers);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    }


    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * @param sessionId
     * @param tag
     * @param member
     * @return 被成功移除的成员的数量，不包括被忽略的成员。
     */
    public Long sortedSet$zrem(final String sessionId, final String tag, String... member) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrem(getSortedSetKey(sessionId, tag), member);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void object$write(String key, Object value, int expiredTime) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ObjectOutputStream outputStream = new ObjectOutputStream(os);
                outputStream.writeObject(value);
                byte[] bValue = os.toByteArray();
                outputStream.close();
                os.close();
                byte[] bKey = key.getBytes();
                jedis.set(bKey, bValue);
                jedis.expire(bKey, expiredTime);
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void object$del(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[] bKey = key.getBytes();
            jedis.del(bKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> T object$get(String key) throws ClassNotFoundException {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[] bKey = key.getBytes();
            byte[] bValue = jedis.get(bKey);
            if (null != bValue) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bValue);
                try {
                    ObjectInputStream in = new ObjectInputStream(inputStream);
                    return (T) in.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void object$remove(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            byte[] bKey = key.getBytes();
            jedis.del(bKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean string$append(String sessionId, String tag, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (value != null) {
                jedis.append(getKey(sessionId, tag), value);
                jedis.expire(getKey(sessionId, tag), expire);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean string$setString(String sessionId, String tag, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (value != null) {
                jedis.set(getKey(sessionId, tag), value);
                jedis.expire(getKey(sessionId, tag), expire);

            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @return
     */
    public long getKeyTTL(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();

            Long ttl = jedis.ttl(key);
            return ttl;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


}
