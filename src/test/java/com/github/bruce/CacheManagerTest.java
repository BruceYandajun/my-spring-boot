package com.github.bruce;

import com.github.bruce.service.UserCommonService;
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

    @Resource
    private UserCommonService userCommonService;

    @Test
    public void redisCacheManager() {
        userService.getUser(1, "a");
        assertTrue(cacheManager.getCacheNames().size() > 1);
    }

    @Test
    public void studentTypeMap() {
        for(int i = 0; i < 10; i ++) {
            System.out.println(i + ": " + userCommonService.getStudentType(12308L));
        }
    }
}
