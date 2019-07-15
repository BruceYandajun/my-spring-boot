package com.github.bruce.service;

import com.github.bruce.model.Book;
import com.github.bruce.model.User;
import com.github.bruce.model.enums.StudentTypeEnum;
import com.github.bruce.service.rpc.UserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRpcService userRpcService;

    @Autowired
    private IStoreService storeService;

    @Cacheable(value = "userInfo", key = "#root.args[0]")
    public User getUserById(Integer id) {
        log.info("user id : " + id);
        User user = new User();
        user.setId(id);
        user.setName("user" + user.getId());
        return user;
    }

    @Cacheable(value = "userInfo", key = "#id+'_'+#name", cacheManager = "redisCacheManager")
    public User getUser(Integer id, String name) {
        log.info("user id : " + id);
        return new User(id, name);
    }

    @CacheEvict(value = "userInfo", key = "#root.args[0]")
    public void updateUser(Integer id){
        System.out.println(id);
    }

    @Cacheable(value = "allUsers")
    public List<User> getAllUsers() {
        return userRpcService.getAllUsers();
    }

    @Cacheable(value = "bookInfo", key = "#id", cacheManager = "redisCacheManager")
    public Book getBookById(Integer id) {
        log.info("book id : " + id);
        Book book = new Book();
        book.setId(id);
        book.setName("book" + book.getId());
        return book;
    }

    public Book getStoreById(Integer id) {
        return storeService.store(id);
    }

    @Cacheable(value = "studentType", key = "#type", cacheManager = "redisCacheManager")
    // String cannot be cast to enum (caching error)
    public StudentTypeEnum getStudentType(Integer type) {
        log.info("studentType type : " + type);
        return StudentTypeEnum.GUEST;
    }

    @Cacheable(value = "studentTypeMap", key = "#studentIds+'_'", cacheManager = "redisCacheManager")
    public Map<String, String> studentTypeMap(String studentIds) {
        log.info("studentIds : " + studentIds);
        Map<String, String> map = new HashMap<>();
        int i = new Random().nextInt(3);
        map.put(studentIds, StudentTypeEnum.values()[i].name());
        return map;
    }
}
