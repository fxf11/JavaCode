package com.fxf.springbootredis2.service;

import com.fxf.springbootredis2.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);
}
