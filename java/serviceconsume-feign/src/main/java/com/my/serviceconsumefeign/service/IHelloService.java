package com.my.serviceconsumefeign.service;

import com.my.serviceconsumefeign.fallback.HelloServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "serviceprovider", fallback = HelloServiceHystrix.class)
public interface IHelloService {
    @RequestMapping(value = "/hello/sayHello", method = RequestMethod.GET)
    String sayHello(@RequestParam(name = "message", required = false) String message);
}
