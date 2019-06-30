package com.github.bruce.controller;

import com.github.bruce.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class AController {

    @Autowired
    private AService aService;

    @RequestMapping("/getById")
    public Object getById(Integer id) {
        return aService.getById(id);
    }

    @RequestMapping("/updateById")
    public Object updateById(Integer id, Integer d) {
        return aService.updateById(id, d);
    }

    @RequestMapping("/insert")
    public Object insert() {
        return aService.insertA();
    }

    @RequestMapping("/findById")
    public Object findById(Integer id) {
        return aService.findById(id);
    }
}
