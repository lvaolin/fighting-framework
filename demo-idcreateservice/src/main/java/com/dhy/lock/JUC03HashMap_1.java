package com.dhy.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lvaolin
 * @create 2019/12/25 5:10 PM
 */
public class JUC03HashMap_1 {

    static Map<String,String> map = new HashMap<>();
    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <10000 ; j++) {
                        map.put("a"+j,"aaaa2"+Thread.currentThread().getName());
                        System.out.println(map.get("a"+j));
                    }

                }
            }).start();

        }

    }
}
