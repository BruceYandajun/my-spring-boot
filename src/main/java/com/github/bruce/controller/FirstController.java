package com.github.bruce.controller;

import com.github.bruce.service.IFirstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 12/15/17.
 *
 * @author yandajun.
 */
@RestController
@RequestMapping("/spring/boot/first")
@ConfigurationProperties(prefix = "amazon")
public class FirstController {

    private final Logger logger = LoggerFactory.getLogger(FirstController.class);

    private Integer associateId;

    @Autowired
    private IFirstService firstService;

    @RequestMapping("/test")
    public Object test() {
        logger.info("---------------enter test method, associateId = {}", associateId);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        Map<String, Object> data = new HashMap<>();
        data.put("test", "test");
        map.put("data", data);
        firstService.service();
        logger.error("--------------error");
        return map;
    }

    public Integer getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Integer associateId) {
        this.associateId = associateId;
    }
}
