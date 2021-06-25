package com.dhy.ratelimit.resilience4j.domain.user;

import java.util.concurrent.TimeoutException;

/**
 * @Project parent
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/6/25 10:19 上午
 */
public class UserEntity {
    private Long userId;
    private String userName;


    public UserEntity(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String say() throws TimeoutException {
        System.out.println("say hello, i am "+userName);
        throw new TimeoutException("time out");
        //return "say hello";
    }
    public void eat(){
        System.out.println("eat food");
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
