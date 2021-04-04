package com.dhy.demo02_mybatis;

public class BossUser {
    private Long id;
    private String userName;
    private String userPwd;
    private Integer userType;

    @Override
    public String toString() {
        return "BossUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userType=" + userType +
                '}';
    }



}
