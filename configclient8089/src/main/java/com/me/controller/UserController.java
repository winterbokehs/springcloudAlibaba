package com.me.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class UserController {
    @Value("${user.name}")
    private String name;
    @Value("${server.port}")
    private int port;

    @GetMapping("/user/getName")
    public String getName(){
        return "当前的用户名："+name+"当前的端口："+port;
    }
}
