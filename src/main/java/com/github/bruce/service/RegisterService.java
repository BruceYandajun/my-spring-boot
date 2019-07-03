package com.github.bruce.service;

import com.github.bruce.dao.entity.UserEntity;
import com.github.bruce.dao.repository.UserRepository;
import com.github.bruce.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class RegisterService {

    private final UserRepository repository;

    @Autowired
    public RegisterService (UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserEntity registerNewUserAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
           return null;
        }
        UserEntity user = new UserEntity();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
//        user.setRoles(Collections.singletonList("ROLE_USER"));
        return repository.save(user);
    }
    private boolean emailExists(String email) {
        UserEntity user = repository.findByEmail(email);
        return user != null;
    }

}
