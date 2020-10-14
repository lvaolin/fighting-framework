package com.dhy.demo01.mapper;

import com.dhy.demo01.dto.User;

public class UserMapperImpl implements UserMapper {
    @Override
    public User getUser() {
        return new User();
    }
}
