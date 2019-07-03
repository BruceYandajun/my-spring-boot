package com.github.bruce.service;

import com.github.bruce.dao.entity.UserEntity;
import com.github.bruce.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Service
//@Transactional
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//    //
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//
//        UserEntity user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found with username: " + email);
//        }
//        return  new User(
//                user.getEmail(),
//                user.getPassword().toLowerCase(),
//                true,
//                true,
//                true,
//                true,
//                getAuthorities(null));
//    }
//
//    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
////        for (String role : roles) {
////            authorities.add(new SimpleGrantedAuthority(role));
////        }
//        return authorities;
//    }
//}
