package com.fxf.springbootredis2;


import com.fxf.springbootredis2.entity.User;
import com.fxf.springbootredis2.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class TestUserService {

    @Resource
    private UserService userService;

    @Test
    public void test(){
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("---------------------");
        userService.findAll().forEach(System.out::println);

    }

    @Test
    public void testById(){
        User user = userService.findById("2");
        System.out.println(user);
        System.out.println("------------------------------------");
        User user1 = userService.findById("2");
        System.out.println(user1);

    }
}
