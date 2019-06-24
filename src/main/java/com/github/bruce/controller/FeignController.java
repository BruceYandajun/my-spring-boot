package com.github.bruce.controller;

import com.github.bruce.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeignController {

    @GetMapping("/getUserName")
    public Object getUserName(Integer id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "user" + id;
    }

    @GetMapping("/getUserId")
    public Object getUserId() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @PostMapping("/updateUser")
    public Object updateUser(User user) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.setName("return1");
        return user;
    }

    @PostMapping("/updateUserJson")
    public Object updateUserJson(@RequestBody User user) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.setName("return2");
        return user;
    }

    @RequestMapping("/getBookName")
    public Object getBookName() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "book";
    }

    @RequestMapping("/getBookId")
    public Object getBookId() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
