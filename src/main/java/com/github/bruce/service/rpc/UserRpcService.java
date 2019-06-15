package com.github.bruce.service.rpc;

import com.github.bruce.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserRpcService {

    public List<User> getAllUsers() {
        log.info("all users");
        User user = new User();
        user.setId(new Random().nextInt(10));
        user.setName("user" + user.getId());
        return Collections.singletonList(user);
    }

}
