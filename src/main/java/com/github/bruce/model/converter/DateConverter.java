package com.github.bruce.model.converter;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateConverter {
    public Long getTime(Date date) {
        return date.getTime();
    }
}