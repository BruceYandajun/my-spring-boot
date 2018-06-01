package com.github.bruce.controller;

import com.github.bruce.model.StudentLombok;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lombok")
public class LombokController {

    @GetMapping("/test")
    public String testLombok() {
        StudentLombok studentLombok = new StudentLombok();
        studentLombok.getAge();
        studentLombok.show();
        return "success";
    }
}
