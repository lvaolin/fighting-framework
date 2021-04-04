package com.dhy.demo01_elasticjob.service;

import com.dhy.demo01_elasticjob.dto.User;
import com.dhy.demo01_elasticjob.itf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
    @Override
    public User getUser(Long id) {
        return null;
    }
}
