package com.dhy.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title MySecurityConfig
 * @Description   http basic 登录方式
 * @Author lvaolin
 * @Date 2021/4/17 19:22
 **/
//@EnableWebSecurity
public class MySecurityConfigForHttpBasic extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存账号密码验证,以及权限集合
        auth.inMemoryAuthentication()
                //密码不加密
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin")
                .password("admin")
                .authorities("role_admin");//权限不能为空

        auth.inMemoryAuthentication()
                //密码不加密
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user")
                .password("user")
                .authorities("role_user");//权限不能为空
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http basic 认证方式，会弹出 浏览器 自己的登录框（简陋）
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated()
                .and().httpBasic();

    }



}
