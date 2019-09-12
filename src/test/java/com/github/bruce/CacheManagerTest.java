package com.github.bruce;

import com.github.bruce.model.User;
import com.github.bruce.service.UserCommonService;
import com.github.bruce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheManagerTest {

    @Resource(name = "redisCacheManager")
    private CacheManager cacheManager;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private UserService userService;

    @Resource
    private UserCommonService userCommonService;

    @Test
    public void redisCacheManager() {
        userService.getMonoUser(0, "a");
        Mono<User> monoUser = userService.getMonoUser(0, "a");
        System.out.println(monoUser.block().getName());
        assertTrue(cacheManager.getCacheNames().size() > 1);
    }

    @Test
    public void studentTypeMap() {
        for(int i = 0; i < 10; i ++) {
            System.out.println(i + ": " + userCommonService.getStudentType(12308L));
        }
    }

    @Test
    public void studentUsers() {
        System.out.println(userCommonService.getStudentUsers("12308,2,50"));
        System.out.println(userCommonService.getStudentUsers("50,2,12308"));
    }

    @Test
    public void getUsers() {
        System.out.println(userCommonService.getUsers(11L, 0));
        System.out.println(userCommonService.getUsers(11L, 1));
    }

    @Test
    public void removeUsers() {
       userCommonService.removeUsersCache(11L, 0);
    }

    @Test
    public void putUsers() {
        userCommonService.putUsersCache(11L, 0);
    }

    @Test
    public void clearCache() {
        cacheManager.getCache("getUsers").clear();
    }

    @Test
    public void removeSomeCache() {
        cacheManager.getCache("getUsers").evict("11_0");
        cacheManager.getCache("getUsers").evict("11_1");
    }

    @Test
    public void removeSomeManagerCache() {
        String cacheManagerName = "redisCacheManager";
        Map<String, CacheManager> cacheManagerMap = applicationContext.getBeansOfType(CacheManager.class);
        CacheManager cacheManager = cacheManagerMap.get(cacheManagerName);
        if (cacheManager == null) {
            return;
        }
        cacheManager.getCache("getUsers").evict("11_0");
        cacheManager.getCache("getUsers").evict("11_1");
    }
}
