package com.github.bruce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/JMeter")
@Slf4j
public class JMeterController {

    @GetMapping("/test")
    public Object test() {
        log.info("JMeter testing");
        return "ok";
    }

}
