package com.dhy.temp;

import java.util.Date;

public class Test0923 {

    /**
     *  * @see     java.lang.Boolean#TYPE
     *      * @see     java.lang.Character#TYPE
     *      * @see     java.lang.Byte#TYPE
     *      * @see     java.lang.Short#TYPE
     *      * @see     java.lang.Integer#TYPE
     *      * @see     java.lang.Long#TYPE
     *      * @see     java.lang.Float#TYPE
     *      * @see     java.lang.Double#TYPE
     *      * @see     java.lang.Void#TYPE
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(isWrapClass(String.class));
        System.out.println(isPrimitive(String.class));
        System.out.println(Long.class.isPrimitive());
        System.out.println(Integer.class.isPrimitive());
        System.out.println(Short.class.isPrimitive());
        System.out.println(Byte.class.isPrimitive());
        System.out.println(String.class.isPrimitive());
    }
    /**
     * 是否基础类型
     *
     * @param cls
     * @return
     */
    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }

    // 是否包装的基本类型
    private static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
