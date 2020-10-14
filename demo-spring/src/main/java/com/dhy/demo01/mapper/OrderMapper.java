package com.dhy.demo01.mapper;

import org.springframework.web.bind.annotation.RequestMapping;

public interface OrderMapper {
    @RequestMapping("select * from order where id = 1")
    Object getOrder();
}
