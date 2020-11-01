package com.dhy.server.itf;

import java.io.Serializable;

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
    private Object parameterValues[];
    /**
     * 调用的方法参数类型列表
     */
    private Object parameterTypes[];


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

    public Object[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Object[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

}
