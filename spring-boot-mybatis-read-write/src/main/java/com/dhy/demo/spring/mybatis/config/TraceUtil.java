package com.dhy.demo.spring.mybatis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  上下文
 */
public class TraceUtil {
    private static Logger log = LoggerFactory.getLogger(TraceUtil.class);

    private static final InheritableThreadLocal<MyContext> myContext = new InheritableThreadLocal(){
        @Override
        protected Object initialValue() {
            return new MyContext();
        }
    };

    public static String getTokenStr() {
        return myContext.get().token;
    }
    public static void setTokenStr(String tokenStr) {
        myContext.get().token = tokenStr;
    }
    public static void setDbKeyMaster(String dbKey) {
        myContext.get().dbKeyMaster = dbKey;
    }

    public static String getDbKeyMaster() {
        return myContext.get().dbKeyMaster;
    }

    public static void setDbKeyReadonly(String dbKey) {
        myContext.get().dbKeyReadonly = dbKey;
    }

    public static String getDbKeyReadonly() {
        return myContext.get().dbKeyReadonly;
    }

    public static void setReadonly(boolean readonly) {
        myContext.get().readonly = readonly;
    }

    public static boolean getReadonly() {
        return myContext.get().readonly;
    }

    static class MyContext{
        public String token;
        public String dbKeyMaster;
        public String dbKeyReadonly;
        public boolean readonly;
    }
}
