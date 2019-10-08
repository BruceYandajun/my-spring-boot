package com.github.bruce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/JMeter")
@Slf4j
public class JMeterController {

    @GetMapping("/test")
    public Object test(@RequestParam Integer id,
                       @RequestHeader(name = "token") String token,
                       @CookieValue(name = "user") String user) {
        log.info("JMeter testing id = {}, token = {}, user = {}", id, token, user);
        return "ok";
    }

}
