package com.github.bruce.controller;

import com.github.bruce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById")
    public String getUserById(@RequestParam int userId) {
        return userService.getUserById(userId).getName();
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam int userId) {
        userService.updateUser(userId);
        return "success";
    }
}
