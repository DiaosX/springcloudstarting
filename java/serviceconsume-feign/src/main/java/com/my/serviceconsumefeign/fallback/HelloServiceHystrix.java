package com.my.serviceconsumefeign.fallback;

import com.my.serviceconsumefeign.service.IHelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloServiceHystrix implements IHelloService {

    @Override
    public String sayHello(@RequestParam(value = "message") String message) {
        return "断路器返回结果:" + message;
    }
}
