package com.github.bruce.service;

import com.alibaba.fastjson.JSON;
import com.github.bruce.model.Book;
import com.github.bruce.model.User;
import com.github.bruce.model.enums.StudentTypeEnum;
import com.github.bruce.service.rpc.UserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
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

    @Resource
    private RedisTemplate<String, Book> redisTemplate;

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

    @Cacheable(value = "users", key = "#id+'_'+#name", unless = "#result.value.id == 0")
    public Mono<User> getMonoUser(Integer id, String name) {
        log.info("user id : " + id + ", name : " + name);
        return Mono.just(new User(id, name));
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

    public Book getBookByIdByRedis(Integer id) {
        log.info("book id by redis: " + id);
        Book book = redisTemplate.opsForValue().get("bookInfo:" + id);
        if (book != null) {
            return book;
        }
        book = new Book();
        book.setId(id);
        book.setName("book" + book.getId());
        redisTemplate.opsForValue().set("bookInfo:" + id, book);
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

    @Cacheable(value = "studentUsers", keyGenerator = "customKeyGenerator", cacheManager = "redisCacheManager")
    public Map<String, User> studentUsers(String studentIds) {
        log.info("studentIds : " + studentIds);
        User user = new User();
        user.setId(new Random().nextInt(10));
        user.setName("Bruce");
        Map<String, User> map = new HashMap<>();
        map.put(studentIds, user);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(Mono.just(new User(1, "a"))));
    }
}
