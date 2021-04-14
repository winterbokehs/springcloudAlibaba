package com.me.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class ProductController {
    @Value("${server.port}")
    private int port;
    @GetMapping("/product/findAll")
    public Map<String,Object> findAll(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg","查询所有商品成功！现在的端口为"+port);
        return map;
    }
}
