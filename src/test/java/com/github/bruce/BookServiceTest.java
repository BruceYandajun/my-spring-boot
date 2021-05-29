package com.github.bruce;

import com.github.bruce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void getBookById() {
        System.out.println(userService.getBookById(1));
    }

    @Test
    public void getBookByIdByRedis() {
        System.out.println(userService.getBookByIdByRedis(2));
    }
}
