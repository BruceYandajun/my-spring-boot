package com.github.bruce.controller;

import com.github.bruce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById")
    public String getBookById(@RequestParam int userId) {
        return userService.getBookById(userId).getName();
    }
}
