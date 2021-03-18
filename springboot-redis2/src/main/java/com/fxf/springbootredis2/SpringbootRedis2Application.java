package com.fxf.springbootredis2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fxf.springbootredis2.dao")
public class SpringbootRedis2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedis2Application.class, args);
    }

}
