package com.fxf.springbootredis2.service;

import com.fxf.springbootredis2.dao.UserDao;
import com.fxf.springbootredis2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional // 增删改要添加事务
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }
}
