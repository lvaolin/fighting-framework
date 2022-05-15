package com.dhy.mlife.common.context;

public class AppContextHolder {

    private static final ThreadLocal<AppContext> appContextHolder = new InheritableThreadLocal<AppContext>();

    public static void set(AppContext appContext) {
        appContextHolder.set(appContext);
    }

    public static void remove() {
        appContextHolder.remove();
    }

    public static AppContext get() {
        return appContextHolder.get();
    }

}
