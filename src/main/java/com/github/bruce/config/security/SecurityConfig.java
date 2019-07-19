package com.github.bruce.config.security;

import com.github.bruce.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // This annotation will disable spring security auto configuration and use myself configuration below
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Add users with different roles in memory for whole system
        PasswordEncoder encoder = encoder();
        auth.inMemoryAuthentication()
                // Add password storage format, for plain text, add {noop}
                .withUser("guest").password(encoder.encode("guest")).roles("GUEST")
                .and()
                .withUser("user").password(encoder.encode("password")).roles("USER", "GUEST")
                .and()
                .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER", "GUEST");

        // Add user from database
        auth.authenticationProvider(authProvider());
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
                 .antMatchers("/user/**").hasRole("USER")
                 .antMatchers("/admin/**").hasRole("ADMIN")
                 .antMatchers("/**").hasRole("GUEST")
                 .and().formLogin();
         http.cors().and().csrf().disable();// Disable cors and csrf attack for permitting post requests
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
