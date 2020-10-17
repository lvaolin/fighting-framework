package com.dhy.demo01_ratelimit.itf;

import com.dhy.demo01_ratelimit.dto.User;

public interface IOpenApiService {
    User queryUser(Long userId);

    User createUser(User user);

    int updateUser(User user);

    int deleteUser(Long userId);
}
