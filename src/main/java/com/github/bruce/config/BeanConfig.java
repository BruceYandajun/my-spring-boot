package com.github.bruce.config;

import com.github.bruce.model.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class BeanConfig {

    @Autowired
    private StaticConfig staticConfig;

    @Bean
    @Order()
    public MyBean myBean() {
        System.out.println(staticConfig.getTestConfig());
        return new MyBean("default");
    }

    @Bean
    @Order(2)
    public MyBean myBean2() {
        System.out.println(staticConfig.getTestConfig());
        return new MyBean("2");
    }

    @Primary
    @Bean
    @Order(3)
    public MyBean myBean3() {
        System.out.println(staticConfig.getTestConfig());
        return new MyBean("3");
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public MyBean highestBean() {
        System.out.println(staticConfig.getTestConfig());
        return new MyBean("highest");
    }
}
