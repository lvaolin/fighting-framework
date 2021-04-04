package com.dhy.demo01_mybatis.service;

import com.dhy.demo01_mybatis.dto.User;
import com.dhy.demo01_mybatis.itf.IUserService;
import com.dhy.demo01_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Object getUser(Long id) {
        return userMapper.getUser();
    }
}
