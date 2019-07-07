package com.github.bruce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // Specify to disable spring security auto configuring using exclude parameter
@SpringBootApplication
@MapperScan("com.github.bruce.dao.mapper")
@PropertySource(value = "classpath:particular.properties", encoding = "UTF-8")
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
