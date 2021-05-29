package com.github.bruce.controller;

import com.github.bruce.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private UserService userService;

    @GetMapping("/getById")
    public String getBookById(@RequestParam int bookId) {
        return userService.getBookById(bookId).getName();
    }

    @GetMapping("/getStoreById")
    public String getStoreById(@RequestParam int storeId) {
        return userService.getStoreById(storeId).getName();
    }

    @GetMapping("/getStudentType")
    public String getStudentType(@RequestParam int type) {
        return userService.getStudentType(type).name();
    }
}
