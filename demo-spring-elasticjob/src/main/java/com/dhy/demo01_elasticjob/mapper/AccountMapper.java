package com.dhy.demo01_elasticjob.mapper;

import com.dhy.demo01_elasticjob.annotation.MySelect;

public interface AccountMapper {
    @MySelect("select * from account where id = 1")
    Object getAccount();
}
