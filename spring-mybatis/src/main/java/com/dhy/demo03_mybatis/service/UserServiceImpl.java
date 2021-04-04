package com.dhy.demo03_mybatis.service;

import com.dhy.demo03_mybatis.BossUser;
import com.dhy.demo03_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public BossUser getUser(Long id) {
        return userMapper.getUser(id);
    }
}
