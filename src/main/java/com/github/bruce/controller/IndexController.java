package com.github.bruce.controller;

import com.alibaba.fastjson.JSON;
import com.github.bruce.model.Book;
import com.github.bruce.model.MyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private StringRedisTemplate stringRedisTemplate;

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
        String s = stringRedisTemplate.opsForValue().get("storeInfo::2");
        Optional.ofNullable(s).ifPresent(a -> {
            Book book = JSON.parseObject(a, Book.class);
            log.info("book's name is " + book.getName());
        });
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
