package com.github.bruce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    @GetMapping("/test")
    public String test() {
        System.out.println("test");

        Runnable t = () -> {
            for (int i = 0; i < 10; i ++) {
                try {
                    System.out.println(i);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadPoolExecutor.submit(t);
        return "test";
    }
}
