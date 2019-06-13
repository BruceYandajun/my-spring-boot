package com.github.bruce.service;

import com.github.bruce.model.Book;
import com.github.bruce.model.User;
import com.github.bruce.service.rpc.UserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRpcService userRpcService;

    @Autowired
    private StoreService storeService;

    @Cacheable(value = "userInfo", key = "#root.args[0]")
    public User getUserById(Integer id) {
        log.info("user id : " + id);
        User user = new User();
        user.setId(id);
        user.setName("user" + user.getId());
        return user;
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

}
