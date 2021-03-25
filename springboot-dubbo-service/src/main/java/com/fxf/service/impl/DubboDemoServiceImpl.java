package com.fxf.service.impl;

import com.fxf.service.DubboDemoService;
import org.springframework.stereotype.Service;

@Service
public class DubboDemoServiceImpl implements DubboDemoService {
    @Override
    public String helloDubbo() {
        return "hello dubbo, I'm server!";
    }
}

