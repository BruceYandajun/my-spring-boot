package com.github.bruce.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public String welcome() {
        return "Welcome to my-spring-boot !";
    }

    /**
     * receive get param 'a', post json param b, for example:
     * /test/post?a=123
     * post json b: {"x":1, "y":2}
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value = "/test/post", method = RequestMethod.POST)
    public String testPost(String a, @RequestBody Map<String, String> b) {
        return a + b;
    }
}
