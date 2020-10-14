package com.dhy.demo01.mapper;

import com.dhy.demo01.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserMapper {
    @RequestMapping("select * from user where id = 1")
    User getUser();
}
