package com.github.bruce.service;

import com.github.bruce.model.User;
import com.github.bruce.model.enums.StudentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.joining;

@Service
@Slf4j
public class UserCommonService {
    @Resource
    private UserService userService;

    public StudentTypeEnum getStudentType(Long studentId) {
        Map<String, String> longStringMap = userService.studentTypeMap(studentId.toString());
        log.info("map: " + longStringMap);
        String studentType = longStringMap.getOrDefault(studentId.toString(), StudentTypeEnum.GUEST.name());
        return StudentTypeEnum.valueOf(studentType);
    }

    public User getStudentUsers(String studentIds) {
        studentIds = Arrays.stream(studentIds.split(",")).map(Long::valueOf).sorted().map(Object::toString).collect(joining(","));
        Map<String, User> longStringMap = userService.studentUsers(studentIds);
        log.info("map: " + longStringMap);
        return longStringMap.getOrDefault(studentIds, new User());
    }

    @Cacheable(value = "getUsers", key = "#studentId+'_'+#source", cacheManager = "redisCacheManager")
    public List<User> getUsers(Long studentId, Integer source) {
        log.info("getUsers studentId = {}, source = {}", studentId, source);
        User user = new User();
        user.setId(new Random().nextInt(10));
        return Collections.singletonList(user);
    }

    @CacheEvict(value = "getUsers", key = "#studentId+'_'+#source", cacheManager = "redisCacheManager")
    public void removeUsersCache(Long studentId, Integer source) {
        log.info("removeUsersCache");
    }

    @CachePut(value = "getUsers", key = "#studentId+'_'+#source", cacheManager = "redisCacheManager")
    public List<User> putUsersCache(Long studentId, Integer source) {
        User user = new User();
        user.setId(new Random().nextInt(10));
        return Collections.singletonList(user);
    }
}
