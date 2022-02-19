package com.dhy.demo.spring.mybatis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceUtil {
    private static Logger log = LoggerFactory.getLogger(TraceUtil.class);

    private static final InheritableThreadLocal<String> dbKey_ = new InheritableThreadLocal<>();

    private static final InheritableThreadLocal<String> token = new InheritableThreadLocal<>();

    public static String getTokenStr() {
        return token.get();
    }
    public static void setTokenStr(String tokenStr) {
        token.set(tokenStr);
    }
    public static void setDbKey(String dbKey) {
        dbKey_.set(dbKey);
    }

    public static String getDbKey() {
        return dbKey_.get();
    }

}
