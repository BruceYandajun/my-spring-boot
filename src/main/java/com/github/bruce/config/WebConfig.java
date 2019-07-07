package com.github.bruce.config;

import com.github.bruce.interceptor.FirstInterceptor;
import com.github.bruce.interceptor.SecondInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FirstInterceptor firstInterceptor(){
        return new FirstInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firstInterceptor()).addPathPatterns("/interceptor/**");
        registry.addInterceptor(secondInterceptor()).addPathPatterns("/interceptor/**");
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addRedirectViewController("/static/abc1.html", "/static/hello.html");
        registry.addStatusController("/error", HttpStatus.INTERNAL_SERVER_ERROR);
        registry.addViewController("/test").setViewName("forward:/login");
        registry.addViewController("/ddd");
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

}
