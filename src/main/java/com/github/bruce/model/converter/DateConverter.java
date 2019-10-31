package com.github.bruce.model.converter;

import java.util.Date;

public class DateConverter {
    public Long getTime(Date date) {
        return date.getTime();
    }
}