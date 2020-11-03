package com.dhy.dubbo.protocol.http;


import com.alibaba.fastjson.JSONObject;
import com.dhy.dubbo.dto.RpcRequest;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServletHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            RpcRequest rpcRequest = JSONObject.parseObject(req.getInputStream(), RpcRequest.class);
            Object result = doInvoke(rpcRequest);
            IOUtils.write(result.toString(),resp.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object doInvoke(RpcRequest rpcRequest) throws Exception {
        System.out.println(rpcRequest.toString());
        Class clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
        Object result = method.invoke(getService(rpcRequest), rpcRequest.getParameterValues());
        return result;
    }

    public Object getService(RpcRequest rpcRequest) {
        //获取要执行方法的 业务对象
        //return MyBeanFactory.getInstance().getBean(rpcRequest.getClassName());
        return null;
    }
}
