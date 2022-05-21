package com.dhy.mlife.billingservice.gateway.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Project spring-boot-starter-demo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/5/11 下午2:13
 */
@Component
public class ConfigImpl implements ConfigI {
    private Map<String, Properties> map = new HashMap<>();

    @Override
    public String getValue(String key) {
        return getValue(key, "application.properties");
    }

    @Override
    public String getValue(String key, String configFile) {
        if (map.get(configFile) == null) {
            synchronized (this) {
                if (map.get(configFile) == null) {
                    Properties resourceAsProperties = ResourceLoader.getResourceAsProperties(configFile);
                    if (resourceAsProperties != null) {
                        map.put(configFile, resourceAsProperties);
                    } else {
                        throw new RuntimeException(configFile + "加载失败");
                    }
                }
            }
        }
        return map.get(configFile).getProperty(key);
    }
}
