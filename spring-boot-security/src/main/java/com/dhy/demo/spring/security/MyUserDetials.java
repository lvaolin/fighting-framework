package com.dhy.demo.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Title MyUserDetials  用户信息
 * @Description
 * @Author lvaolin
 * @Date 2021/4/17 19:27
 **/
public class MyUserDetials implements UserDetails {

    public MyUserDetials() {
    }

    public MyUserDetials(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    private String username;
    private String password;
    /**
     * 权限明细
     */
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 账号是否被禁用
     */
    private boolean enabled = true;
    /**
     * 密码是否过期
     */
    private boolean credentialsNonExpired = true;
    /**
     * 账号是否被锁定
     */
    private boolean accountNonLocked = true;
    /**
     * 账号是否过期
     */
    private boolean accountNonExpired = true;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }


    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
