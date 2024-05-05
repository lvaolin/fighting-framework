package com.dhy.oom.demos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private IdService idService;
    public void addUser(String name,Integer age){
        String id = idService.getId();
        System.out.println("add user id:"+id+" name:"+name+",age:"+age);
    }
    public void removeUser(String name,Integer age){
        System.out.println("remove user name:"+name+",age:"+age);
    }

    public void updateUser(String name,Integer age){
        System.out.println("update user name:"+name+",age:"+age);
    }
    public void queryUser(String name,Integer age){
        System.out.println("query user name:"+name+",age:"+age);
    }
    public void queryUser(String name){
        System.out.println("query user name:"+name);
    }
    public void queryUser(){
        System.out.println("query user");
    }
    public void queryUser(Integer age){
        System.out.println("query user age:"+age);
    }
    public void queryUser(String name,String age){
        System.out.println("query user name:"+name+",age:"+age);
    }

}
