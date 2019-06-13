package com.github.bruce.controller;

import com.github.bruce.model.MyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Description
 * <p>
 * </p>
 * DATE 2/2/18.
 *
 * @author yandajun.
 */
@RestController
@Slf4j
public class IndexController {

    @Autowired
    private MyBean myBean;

    @Autowired
    private List<MyBean> myBeans;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String welcome() {
        log.info(myBean.getName()); // 3
        log.info(myBeans.stream().map(b -> b.getName()).collect(toList()).toString()); // [highest, 2, 3, default]
        return "Welcome to my-spring-boot !";
    }

    @RequestMapping("/managers")
    public String managers() {
        log.info(cacheManager.toString());
        log.info(redisTemplate.toString());
        reactiveRedisTemplate.opsForValue().set("test", "abc").block();
        log.info(redisTemplate.hasKey("test").toString());
        return cacheManager.toString();
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
