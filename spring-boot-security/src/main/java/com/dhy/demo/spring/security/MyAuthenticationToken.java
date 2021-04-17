package com.dhy.demo.spring.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Title MyAuthenticationToken
 * @Description
 * @Author lvaolin
 * @Date 2021/4/17 19:40
 **/
public class MyAuthenticationToken extends AbstractAuthenticationToken {
    public MyAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
