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

    public static String getShardingTableColumnValue() {
        return myContext.get().getShardingTableColumnValue();
    }

    public static String getTokenStr() {
        return myContext.get().getToken();
    }
    public static void setTokenStr(String tokenStr) {
        myContext.get().setToken(tokenStr);
    }
    public static void setDbKeyMaster(String dbKey) {
        myContext.get().setDbKeyMaster(dbKey);
    }

    public static String getDbKeyMaster() {
        return myContext.get().getDbKeyMaster();
    }

    public static void setDbKeyReadonly(String dbKey) {
        myContext.get().setDbKeyReadonly(dbKey);
    }

    public static String getDbKeyReadonly() {
        return myContext.get().getDbKeyReadonly();
    }

    public static void setReadonly(boolean readonly) {
        myContext.get().setReadonly(readonly);
    }

    public static boolean getReadonly() {
        return myContext.get().isReadonly();
    }

    static class MyContext{
        private String token;
        private String dbKeyMaster;
        private String dbKeyReadonly;
        private boolean readonly;
        private String shardingTableColumnValue;
        public String getShardingTableColumnValue() {
            if (shardingTableColumnValue == null) {
                //第一次用从token解析出 shardingTableColumnValue
                //shardingTableColumnValue = parse(token);
            }
            return shardingTableColumnValue;
        }

        public void setShardingTableColumnValue(String shardingTableColumnValue) {
            this.shardingTableColumnValue = shardingTableColumnValue;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDbKeyMaster() {
            return dbKeyMaster;
        }

        public void setDbKeyMaster(String dbKeyMaster) {
            this.dbKeyMaster = dbKeyMaster;
        }

        public String getDbKeyReadonly() {
            return dbKeyReadonly;
        }

        public void setDbKeyReadonly(String dbKeyReadonly) {
            this.dbKeyReadonly = dbKeyReadonly;
        }

        public boolean isReadonly() {
            return readonly;
        }

        public void setReadonly(boolean readonly) {
            this.readonly = readonly;
        }

    }
}
