package com.dhy.common;


public class MyImportBean2 {

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

    private Long id;
    private String name;

    @Override
    public String toString() {
        return "MyImportBean2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
