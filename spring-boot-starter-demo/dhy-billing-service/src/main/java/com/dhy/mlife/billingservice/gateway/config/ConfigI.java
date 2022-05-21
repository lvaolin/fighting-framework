package com.dhy.mlife.billingservice.gateway.config;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/11 下午2:11
 */
public interface ConfigI {
    String getValue(String key);

    String getValue(String key, String configFile);
}
