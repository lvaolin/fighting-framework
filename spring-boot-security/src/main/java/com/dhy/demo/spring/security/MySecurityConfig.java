package com.dhy.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Title MySecurityConfig
 * @Description
 * @Author lvaolin
 * @Date 2021/4/17 19:22
 **/
@Configuration
public class MySecurityConfig {
    /**
     * security 配置
     * @param myAuthenticationProvider
     * @return
     */

    @Autowired
    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter(MyAuthenticationProvider myAuthenticationProvider) {
        /**
         * 配置对哪些路径进行拦截,如果方法里面什么都不写，则不拦截任何路径；
         * <p>
         * 如果，使用 super.configure(http)，父类的方法：
         * ((HttpSecurity)((HttpSecurity)((AuthorizedUrl)http.authorizeRequests().anyRequest()).authenticated().and()).formLogin().and()).httpBasic();
         * <p>
         * 我们自定义下拦截规则,表单等一系列规则；
         */
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                        .authorizeRequests()
                        // 放行登录
                        .antMatchers("/login/**").permitAll()
                        .antMatchers("/admin/*").hasRole("role_admin")//不同角色访问不同路径
                        .antMatchers("/user/*").hasRole("role_user")
                        .antMatchers("/shopping/*").hasAnyRole("role_admin","role_user","role_shopping")
                        .anyRequest().authenticated()
                        .and()
                        // 开启表单认证
                        .formLogin()
                        // 地址写的是 映射的路径
                        .loginPage("/login.html")
                        // 必须添加
                        .loginProcessingUrl("/login")
                        .permitAll()
                        // 第二个参数，如果不写成true，则默认登录成功以后，访问之前被拦截的页面，而非去我们规定的页面
                        .defaultSuccessUrl("/index.html", true)
                        .and()

                        .logout()
                        .logoutUrl("/logout")
                        .and()
                        .csrf()
                        .disable()
                        .httpBasic();

            }

            /**
             * 配置自定义校验规则，密码编码，使用我们自定义的校验器
             * @param auth
             * @throws Exception
             */
            @Override
            protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.authenticationProvider(myAuthenticationProvider);
            }


        };
    }
}
