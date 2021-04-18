package com.dhy.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 从数据库根据用户名加载用户
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if("root".equalsIgnoreCase(name)){
            // 添加角色
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("role_saas"));
            MyUserDetials myUserDetials = new MyUserDetials("root","root",authorities);
            return  myUserDetials;

        }

        if("one".equalsIgnoreCase(name)){
            // 添加角色
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("role_one"));

            MyUserDetials myUserDetials = new MyUserDetials("one","one",authorities);
            return  myUserDetials;
        }

        throw new UsernameNotFoundException("用户不存在") ;



    }
}

