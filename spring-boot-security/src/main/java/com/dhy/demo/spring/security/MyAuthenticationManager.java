package com.dhy.demo.spring.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Title My1
 * @Description
 * @Author lvaolin
 * @Date 2021/4/17 19:38
 **/
public class MyAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
