package com.github.bruce.controller;

import com.alibaba.fastjson.JSON;
import com.github.bruce.model.Book;
import com.github.bruce.model.MyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
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

    @Resource
    private RedisCacheManager redisCacheManager;

    @Resource
    private List<CacheManager> managers;

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String welcome() {
        log.info(myBean.getName()); // 3
        log.info(myBeans.stream().map(MyBean::getName).collect(toList()).toString()); // [highest, 2, 3, default]
        return "Welcome to my-spring-boot !\n";
    }

    @RequestMapping("/cacheManager")
    public String cacheManager() {
        log.info(cacheManager.toString());
        log.info(redisTemplate.toString());
        reactiveRedisTemplate.opsForValue().set("test", "abc").block();
        String s = stringRedisTemplate.opsForValue().get("storeInfo::2");
        Optional.of(s).ifPresent(a -> {
            Book book = JSON.parseObject(a, Book.class);
            log.info("book's name is " + book.getName());
        });
        log.info(redisTemplate.hasKey("test").toString());
        return cacheManager.toString();
    }

    @RequestMapping("/managers")
    public Object managers() {
        return managers.stream().map(Object::toString).collect(joining("\n"));
    }

    @RequestMapping("/removeRedisCache")
    public Object removeRedisCache(String key) {
        Optional.ofNullable(redisCacheManager).map(manager -> manager.getCache(key)).ifPresent(Cache::clear);
        return true;
    }

    /**
     * receive get param 'a', post json param b, for example:
     * /test/post?a=123
     * post json b: {"x":1, "y":2}
     * @param a a
     * @param b b
     * @return c
     */
    @RequestMapping(value = "/test/post", method = RequestMethod.POST)
    public String testPost(String a, @RequestBody Map<String, String> b) {
        return a + b;
    }
}
