package com.dhy.mlife.billingservice.gateway.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class ContextLoader {

    public static Properties CONTEXT_PROPS;

    static {
        final String FILE_CONTEXT = "config/app.properties";
        final String PROP_CONTEXT = "context";
        Properties prop = null;
        String ctx = null;

        try {
            prop = ResourceLoader.getResourceAsProperties(FILE_CONTEXT);
            ctx = prop.getProperty(PROP_CONTEXT);
            log.info("Context file: " + ctx);
            CONTEXT_PROPS = ResourceLoader.getResourceAsProperties(ctx);
        } catch (Exception e) {
            log.warn("Load context properties file error: "
                    + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        return CONTEXT_PROPS.getProperty(key);
    }

    public static int getPropertyAsInt(String key) {
        return Integer.parseInt(CONTEXT_PROPS.getProperty(key));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ContextLoader.getProperty("server.port"));
    }

}
