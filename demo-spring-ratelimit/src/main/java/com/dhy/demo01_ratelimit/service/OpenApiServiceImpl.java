package com.dhy.demo01_ratelimit.service;

import com.dhy.demo01_ratelimit.annotation.MyRateLimit;
import com.dhy.demo01_ratelimit.dto.User;
import com.dhy.demo01_ratelimit.itf.IOpenApiService;
import org.springframework.stereotype.Component;

@Component
public class OpenApiServiceImpl implements IOpenApiService {
    @MyRateLimit
    @Override
    public User createUser(User user) {
        System.out.println("create User----ok-----"+user.getName());
        return user==null?new User():user;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(Long userId) {
        return 0;
    }
}
