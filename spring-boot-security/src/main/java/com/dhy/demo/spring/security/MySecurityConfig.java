package com.dhy.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Title MySecurityConfig
 * @Description
 * @Author lvaolin
 * @Date 2021/4/17 19:22
 **/
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(myAuthenticationProvider);
        auth.userDetailsService(myUserDetailService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
//    @Autowired
//    private MyAuthenticationProvider myAuthenticationProvider;
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()//公开页面，首页，登录页，浏览商品页
                .antMatchers("/mall/*").hasAnyRole("role_user")//商城购买产品相关
                .antMatchers("/dz/*").hasAnyRole("role_dz")//代账管理相关
                .antMatchers("/batch/*").hasAnyRole("role_batch", "role_dz")//批量操作相关
                .antMatchers("/one/*").hasAnyRole("role_one", "role_batch", "role_dz")//单户操作相关
                .antMatchers("/saas/*").hasAnyRole("role_saas")//saas平台管理员
                .antMatchers("/boss/*").hasAnyRole("role_boss", "role_saas");//运营系统
               // .anyRequest().authenticated();
//                .and()
//                // 开启表单认证
//                .formLogin()
//                // 地址写的是 映射的路径
//                .loginPage("/login.html")
//                // 必须添加
//                .loginProcessingUrl("/login")
//                .permitAll()
//                // 第二个参数，如果不写成true，则默认登录成功以后，访问之前被拦截的页面，而非去我们规定的页面
//                .defaultSuccessUrl("/index.html", true)
//                .and()
//
//                .logout()
//                .logoutUrl("/logout")
//                .and()
//                .csrf()
//                .disable()
//                .httpBasic();

    }

}
