package com.dhy.json;

public class Test03 {

    public static void main(String[] args) throws Exception {
        System.out.println((char)65);
        System.out.println(isWrapClass(Long.class));
        System.out.println(isWrapClass(Integer.class));
        System.out.println(isWrapClass(String.class));
        System.out.println(isWrapClass(Test03.class));
        System.out.println(String.class.getCanonicalName());
    }

    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
