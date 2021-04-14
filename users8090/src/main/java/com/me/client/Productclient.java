package com.me.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

//调用商品服务的组件
@FeignClient(value = "products")   //标识该接口是一个feign组件   value为调用服务的  id
public interface Productclient {

    @GetMapping("/product/findAll")  //服务中具体方法的名称保持一致
    Map<String,Object> findAll();
}
