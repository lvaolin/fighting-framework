package com.dhy.demo01.mapper;

import org.springframework.web.bind.annotation.RequestMapping;

public interface AccountMapper {
    @RequestMapping("select * from account where id = 1")
    Object getAccount();
}
