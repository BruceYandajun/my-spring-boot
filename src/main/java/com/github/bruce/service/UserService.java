package com.github.bruce.service;

import com.github.bruce.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Cacheable(value = "getUserById", key = "#root.args[0]")
    @TimeToLive
    public User getUserById(Integer id) {
        User user = new User();
        int sid = new Random().nextInt(id);
        System.out.println("id :" + sid);
        user.setId(sid);
        user.setName("user" + user.getId());
        return user;
    }

    @CacheEvict(value = "getUserById", key = "#root.args[0]")
    public void updateUser(Integer id){
        System.out.println(id);
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
