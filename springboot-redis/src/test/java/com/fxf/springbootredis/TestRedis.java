package com.fxf.springbootredis;

import com.fxf.springbootredis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = SpringbootRedisApplication.class)
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testRedisTemplate(){

        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.opsForValue().set("user",new User("小范",20,new Date()));
        User user = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user);
        Set keys = redisTemplate.keys("*");
        keys.forEach(key -> System.out.println(key));
        /*Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);*/

        redisTemplate.opsForList().leftPush("list",new User("小猪",20,new Date()));


    }

    @Test
    public void testBound(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        BoundValueOperations<String,String> name = redisTemplate.boundValueOps("name");
//        name.set("zhoujielun");
//        name.append("真的很帅");
        String s = name.get();
        System.out.println(s);

        BoundListOperations<String,String> list = redisTemplate.boundListOps("lists");
        list.leftPushAll("小范","小强","小猪");
        List<String> range = list.range(0, -1);
        for (String s1 : range) {
            System.out.println(s1);
        }

        BoundZSetOperations<String,String> zset = redisTemplate.boundZSetOps("zset");
        zset.add("小范",10);
        zset.range(0,-1);




    }
}
