package com.dhy.mlife.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.dhy.mlife.common.core.MyResponseData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
//        return  (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
//                returnType.hasMethodAnnotation(ResponseBody.class));
        return true;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        System.out.println("包装响应数据----");
        MyResponseData result  = new MyResponseData(returnValue);
        String resultString = JSON.toJSONString(result);

        mavContainer.setRequestHandled(true);
//        ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);
//        outputMessage.getServletResponse().getWriter().append(resultString);

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().append(resultString);

    }

    protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(servletRequest != null, "No HttpServletRequest");
        return new ServletServerHttpRequest(servletRequest);
    }

    protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        return new ServletServerHttpResponse(response);
    }
}