package com.dhy.demo.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title MyFailureHandle
 * @Description
 * @Author lvaolin
 * @Date 2021/4/24 23:04
 **/
@Component
public class MyFailureHandle implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        System.out.println("登录失败");
        res.sendRedirect("/public/loginFailure.html");
    }
}
