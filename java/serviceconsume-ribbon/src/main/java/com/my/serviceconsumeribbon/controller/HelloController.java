package com.my.serviceconsumeribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/sayHello")
    public String sayHello(String message) {
        //  return "hello world!";
        //Ribbon默认的服负载均衡为轮训
        String url = "http://serviceprovider/hello/sayHello?message=" + message;
        return restTemplate.getForObject(url, String.class);
    }
}
