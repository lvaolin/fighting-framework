package com.dhy.temp.beaninfor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TestBeanInfo {

    @Test
    @DisplayName("内省机制赋值取值测试")
    public  void neixing() throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        User user = User.class.newInstance();
        //为指定的属性赋值取值
        PropertyDescriptor name = new PropertyDescriptor("name", User.class);
        name.getWriteMethod().invoke(user,"dahuangya");
        Object invoke = name.getReadMethod().invoke(user);
        System.out.println(invoke);

        //轮询所有的属性赋值取值
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            if (propertyDescriptor.getPropertyType().getTypeName().equals("java.lang.Integer")) {
                propertyDescriptor.getWriteMethod().invoke(user,12);
            }
            if (propertyDescriptor.getPropertyType().getTypeName().equals("java.lang.String")) {
                propertyDescriptor.getWriteMethod().invoke(user,"dahuangya");
            }
        }

        System.out.println(user.getCode());
        System.out.println(user.getName());

    }

    @Test
    @DisplayName("反射机制赋值取值测试")
    public  void fanshe() throws IllegalAccessException, InstantiationException {
        User user = User.class.newInstance();
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().getTypeName().equals("java.lang.Integer")) {
                field.set(user,18);
            }
            if (field.getType().getTypeName().equals("java.lang.String")) {
                field.set(user,"dhy");
            }

        }

        for (Field field : fields) {
            System.out.println(field.get(user));
        }

    }
    static class User{
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        private Integer code;
        private String name;

    }

}
