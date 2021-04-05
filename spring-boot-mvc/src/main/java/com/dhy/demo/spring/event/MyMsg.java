package com.dhy.demo.spring.event;

import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import java.util.List;

/**
 * @Title MyMsg  消息
 * @Description
 * @Author lvaolin
 * @Date 2021/4/5 10:56
 **/
public class MyMsg implements Serializable {

     private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private String name;
     private Integer age;
     private List<String> tags;

}
