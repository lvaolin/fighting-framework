package com.dhy.demo.spring.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Title MySecurityConfig
 * @Description   form login 登录方式 (spring 提供默认登录框)
 * @Author lvaolin
 * @Date 2021/4/17 19:22
 **/
//@EnableWebSecurity
public class MySecurityConfigForFromLogin extends WebSecurityConfigurerAdapter {

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
        //        //form login 认证方式，会弹出 spring security 自带的登录框（一般）
          http.authorizeRequests().antMatchers("/**").fullyAuthenticated()
                  .and().formLogin();


    }



}
