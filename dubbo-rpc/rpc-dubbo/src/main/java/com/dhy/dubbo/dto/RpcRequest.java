package com.dhy.dubbo.dto;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {
    /**\
     * 反射的类名称
     */
    private String className;
    /**
     * 反射的方法名称
     */
    private String methodName;
    /**
     * 调用的方法参数值
     */
    private Object[] parameterValues;

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    /**
     * 调用的方法参数类型列表
     */
    private Class<?>[] parameterTypes;

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterValues=" + Arrays.toString(parameterValues) +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(Object[] parameterValues) {
        this.parameterValues = parameterValues;
    }



}
