package com.dhy.temp;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Parameter;
import java.util.*;

public class TestFastjson814 {
    private boolean isCollectionType(Parameter parameter) {
        return Collection.class.isAssignableFrom(parameter.getType());
    }
    public static void main(String[] args) {

        System.out.println(Collection.class.isAssignableFrom(Map.class));
        System.out.println(Collection.class.isAssignableFrom(Set.class));
        System.out.println(Collection.class.isAssignableFrom(HashSet.class));
        System.out.println(Collection.class.isAssignableFrom(List.class));
        System.out.println(Collection.class.isAssignableFrom(ArrayList.class));
        System.out.println(Collection.class.isAssignableFrom(LinkedList.class));
        System.out.println(Map.class.isAssignableFrom(HashMap.class));

        String bodyDataStr1="{year: \"2020\", period: \"07\"}";
        Object obj1 = JSONObject.parseObject(bodyDataStr1, Map.class);
        System.out.println(obj1);

        String bodyDataStr="{\"inventoryAccountPageSize\":50}";
        Object obj = JSONObject.parseObject("{\"inventoryAccountPageSize\":50}", String.class);
        System.out.println(obj);

        System.out.println(int.class.getCanonicalName());
        System.out.println(Integer.class.getCanonicalName());
        System.out.println(boolean.class.getCanonicalName());
        System.out.println(Boolean.class.getCanonicalName());
        System.out.println(String.class.getCanonicalName());
        System.out.println(double.class.getCanonicalName());
        System.out.println(Double.class.getCanonicalName());

        System.out.println(isPrimitive(Byte.class));
        System.out.println(isPrimitive(byte.class));
        System.out.println(isPrimitive(Integer.class));
        System.out.println(isPrimitive(int.class));
        System.out.println(isPrimitive(Short.class));
        System.out.println(isPrimitive(short.class));
        System.out.println(isPrimitive(Float.class));
        System.out.println(isPrimitive(float.class));
        System.out.println(isPrimitive(Double.class));
        System.out.println(isPrimitive(double.class));
        System.out.println(isPrimitive(Long.class));
        System.out.println(isPrimitive(long.class));
        System.out.println(isPrimitive(String.class));
        System.out.println(isPrimitive(Date.class));

    }

    /**
     * 是否基本类型（包括基本类型、包装类型、String、Boolean、Date等）
     * @param cls
     * @return
     */
    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }
}
