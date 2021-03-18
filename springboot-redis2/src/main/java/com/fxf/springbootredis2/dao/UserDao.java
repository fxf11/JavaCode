package com.fxf.springbootredis2.dao;

import com.fxf.springbootredis2.entity.User;

import java.util.List;


public interface UserDao {

    List<User> findAll();


    User findById(String s);
}
