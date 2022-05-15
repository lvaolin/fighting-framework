package com.dhy.mlife.billingservice.gateway.cache.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Project spring-boot-starter-demo
 * @Description redis客户端配置信息
 * @Author lvaolin
 * @Date 2022/5/11 上午9:30
 */
@ConfigurationProperties(prefix = "redis")
@Configuration
@Data
@Slf4j
public class RedisConfig {
    private String host;
    private String port;
    private String pass;
    private String timeout;
    private String poolMaxTotal;
    private String poolMaxActive;
    private String poolMaxIdle;
    private String poolMaxWait;
    private String poolTestOnBorrow;
    private String poolTestOnReturn;

    private JedisPool pool = null;

    public RedisConfig() {
        System.out.println("--------");
    }

    public JedisPool getJedisPool() {
        if (pool == null && host != null && !"".equals(host) && !"${redis_host}".equals(host) && port != null && !"".equals(port) && !"${redis_port}".equals(port)) {
            try {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxIdle(Integer.parseInt(poolMaxIdle));
                config.setTestOnBorrow("true".equalsIgnoreCase(poolTestOnBorrow));
                config.setTestOnReturn("true".equalsIgnoreCase(poolTestOnReturn));
                config.setMaxTotal(Integer.parseInt(poolMaxTotal));
                pool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("远程缓存服务器连接失败!" + e.getMessage());
            }
        } else {
            log.error("缺少缓存服务器配置信息!请在application.properties中进行配置");
        }
        return pool;
    }
}
