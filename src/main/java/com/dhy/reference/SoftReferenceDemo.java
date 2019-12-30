package com.dhy.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 软引用一般用在缓存数据上
 * 当jvm内存足够时这些对象不会被回收
 * 当jvm内存不足时（gc过后仍然没有足够内存）这些对象会被回收
 *
 *
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        // 强引用
        String str="1234";
        // 软引用
        SoftReference<String> softRef=new SoftReference<String>("1234");
        System.out.println(softRef.get());
        List<String> aa = new ArrayList<>();
        aa.add("a");
        aa.add("b");
        SoftReference<List<String>> sss = new SoftReference<List<String>>(aa);

        for (int i = 0; i < sss.get().size(); i++) {
            System.out.println(sss.get().get(i));
        }


        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","20");

        SoftReference<Map> mapSoftReference = new SoftReference<>(map);

        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry.getValue());
        }



    }


}
