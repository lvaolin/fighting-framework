package com.dhy.demo01_elasticjob.mapper;

import com.dhy.demo01_elasticjob.annotation.MySelect;

public interface OrderMapper {
    @MySelect("select * from order where id = 1")
    Object getOrder();
}
