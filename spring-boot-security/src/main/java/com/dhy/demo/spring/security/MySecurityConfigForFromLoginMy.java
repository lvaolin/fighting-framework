package com.dhy.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Title MySecurityConfig
 * @Description   form login 登录方式 (自定义登录框)
 * @Author lvaolin
 * @Date 2021/4/17 19:22
 **/
@EnableWebSecurity
public class MySecurityConfigForFromLoginMy extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySuccessHandle mySuccessHandle;


    @Autowired
    private MyFailureHandle myFailureHandle;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存账号密码验证,以及权限集合
        auth.inMemoryAuthentication()
                //密码不加密
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin")
                .password("admin")
                .authorities("admin");//权限不能为空

        auth.inMemoryAuthentication()
                //密码不加密
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user")
                .password("user")
                .authorities("user");//权限不能为空
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        //form login 认证方式，会弹出 spring security 自带的登录框（一般）
          http.authorizeRequests()
                 //登录 action 要排除校验
                  //.antMatchers("/login").permitAll()
                  .antMatchers("/public/**").permitAll()
                  .antMatchers("/saas/**").hasAnyRole("saas","admin")
                  .antMatchers("/one/**").hasAnyRole("user")
                  .antMatchers("/**").fullyAuthenticated()
                  .and()
                  //登录方式  form 表单
                  .formLogin()
                  //自定义登录页面
                  .loginPage("/public/myLogin.html")
                  //username对应的text名称，默认为username
                  .usernameParameter("username")
                  //password对应的text名称，默认为password
                  .passwordParameter("password")
                  //form 表单 action提交路径
                  .loginProcessingUrl("/login")
                  //登录相关的url不做登录校验
                  .permitAll()
                  //方式一
                  .successHandler(mySuccessHandle)
                  .failureHandler(myFailureHandle)
                  //方式二
//                  //登录成功后跳转的页面(有不支持post请求问题)
//                  .successForwardUrl("/public/loginSuccess.html")
//                  //登录失败后跳转的页面
//                  .failureForwardUrl("/public/loginFailure.html")
                  .and().csrf().disable()//如果启用，登录必须传隐藏参数_csrf ，相当于验证码
          ;


    }



}
