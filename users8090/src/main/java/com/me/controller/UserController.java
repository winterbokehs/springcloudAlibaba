package com.me.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.me.client.Productclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private Productclient productclient;
    @Value("${server.port}")
    private int port;

    @GetMapping("/user/findAll")
    @SentinelResource(value = "/user/findAll",blockHandler = "findAllblockHandler",fallback = "findAllfallback")
    public Map<String,Object> findAll(){
        //调用商品端的服务
        Map<String, Object> map = productclient.findAll();
        map.put("str","user端服务");
        map.put("port",port);
        return map;
    }
    //处理降级 | 流控异常的自定义方法
    public Map<String,Object> findAllblockHandler(BlockException blockException){
        Map<String, Object> map = new HashMap<>();
        if (blockException instanceof FlowException){
            map.put("str","当前服务已被限流！"+blockException.getClass().getName());
        }
        if (blockException instanceof DegradeException){
            map.put("str","当前服务已被降级！"+blockException.getClass().getName());
        }
        return map;
    }
//处理抛出的异常
    public Map<String,Object> findAllfallback(){
        Map<String, Object> map = new HashMap<>();
        map.put("str","product端未启动，请稍后重试");
         return map;
    }
}
