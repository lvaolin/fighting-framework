package com.dhy.demo01_mybatis.itf;

import com.dhy.demo01_mybatis.dto.User;

public interface IUserService {
    User getUser(Long id);
}
