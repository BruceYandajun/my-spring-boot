package com.github.bruce.service;

import com.github.bruce.dao.entity.UserEntity;
import com.github.bruce.dao.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
//    @Cacheable(value = "user-login", key = "#email", cacheManager = "redisCacheManager")
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity entity = userRepository.findByEmail(email);
        if (entity == null) {
            throw new UsernameNotFoundException("No entity found with username: " + email);
        }
//        User user = new User(
//                entity.getEmail(),
//                entity.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                getAuthorities(entity.getRoles()));
        return User.builder()
                .username(entity.getEmail())
                .password(entity.getPassword())
                .roles(entity.getRoles().split(",")).build();
    }

    private static List<GrantedAuthority> getAuthorities (String roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles.split(",")) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
