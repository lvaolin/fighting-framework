package com.dhy.util.peakcut.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LocalRAMCache {
    private static Map<String,Object> cachemap = new ConcurrentHashMap<>();

    public static Map<String,Object> getCachemap() {
        return cachemap;
    }

    public static Object get(String key) {
        return cachemap.get(key);
    }

    public static void delete(String key) {
        cachemap.remove(key);
    }

    public static void set(String key, Object object) {
        cachemap.put(key, object);
    }

}
