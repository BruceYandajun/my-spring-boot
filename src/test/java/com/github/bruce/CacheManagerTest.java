package com.github.bruce;

import com.github.bruce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheManagerTest {

    @Resource(name = "redisCacheManager")
    private CacheManager cacheManager;

    @Resource
    private UserService userService;

    @Test
    public void redisCacheManager() {
        userService.getUser(1, "a");
        assertTrue(cacheManager.getCacheNames().size() > 1);
    }
}
