package com.github.bruce.controller;

import com.github.bruce.model.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BeanController {

    @Autowired
    private MyBean myBean;

    @GetMapping("/myBean")
    public String myBean() {
        return myBean.getName();
    }
}
