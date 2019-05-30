package com.my.serviceprovider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/sayHello")
    public String sayHello(String message) {
        System.out.println("此实例提供服务.");
        return "hello," + message;
    }
}
