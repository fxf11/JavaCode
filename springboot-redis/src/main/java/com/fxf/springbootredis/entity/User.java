package com.fxf.springbootredis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private String name;
    private Integer age;
    private Date date;

    public User(String name, Integer age, Date date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }
}
