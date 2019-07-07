package com.github.bruce.service;

import com.github.bruce.dao.entity.UserEntity;
import com.github.bruce.dao.repository.UserRepository;
import com.github.bruce.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    @Autowired
    public RegisterService (UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity registerNewUserAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
           return null;
        }
        UserEntity user = new UserEntity();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles("USER,GUEST");
        return repository.save(user);
    }
    private boolean emailExists(String email) {
        UserEntity user = repository.findByEmail(email);
        return user != null;
    }

}
