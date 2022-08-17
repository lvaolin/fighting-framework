package com.dhy.demo.spring.service.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Project parent
 * @Description 多例bean
 * @Author lvaolin
 * @Date 2022/8/17 上午9:00
 */
@Component
@Scope("prototype")
public class BillInfoService {
    private String flag="1";

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void showInfo(){
        System.out.println(flag);
    }
}
