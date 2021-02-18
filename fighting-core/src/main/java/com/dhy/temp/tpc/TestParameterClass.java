package com.dhy.temp.tpc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

public class TestParameterClass {

    public static void main(String[] args) {

        Method[] declaredMethods = IBuService.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {

            Parameter[] parameters = declaredMethod.getParameters();
            for (Parameter parameter : parameters) {

                System.out.println(parameter.getType().getCanonicalName());
                System.out.println(isPrimitive(parameter.getType()));
                System.out.println(Token.class.getName().equals(parameter.getParameterizedType().getTypeName()));
            }
        }

    }

    /**
     * 是否基础类型
     * @param cls
     * @return
     */
    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }
}



