package com.my.serviceprovider.controller;

import com.my.serviceprovider.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    public static Map<String, User> userMap = new HashMap<>();

    public UserController() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setAge(i);
            userMap.put(user.getName(), user);
        }
    }

    @RequestMapping("/getUserByName")
    public User getUserByName(String name) {
        if (userMap.containsKey(name)) {
            return userMap.get(name);
        }
        User user = new User();
        return user;
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}
