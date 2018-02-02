package com.github.bruce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * <p>
 * </p>
 * DATE 2/2/18.
 *
 * @author yandajun.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String welcome(){
        return "Welcome to my-spring-boot !";
    }
}
