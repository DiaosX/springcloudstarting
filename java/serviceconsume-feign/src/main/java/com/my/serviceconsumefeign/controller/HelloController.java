package com.my.serviceconsumefeign.controller;

import com.my.serviceconsumefeign.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")

public class HelloController {

    @Autowired
    private IHelloService helloService;

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam(required = false) String message) {
        return helloService.sayHello(message);
    }
}
