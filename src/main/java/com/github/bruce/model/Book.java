package com.github.bruce.model;

import lombok.Data;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private Integer id;
    private String name;

    @TimeToLive
    private int expire;
}
