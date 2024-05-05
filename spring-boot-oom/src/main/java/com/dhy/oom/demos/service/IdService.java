package com.dhy.oom.demos.service;

import org.springframework.stereotype.Component;

@Component
public class IdService {
    public String getId()
    {
        return "id";
    }
    public String getId(String name)
    {
        return "id"+name;
    }
    public String getId(String name,String age)
    {
        return "id"+name+age;
    }
}
