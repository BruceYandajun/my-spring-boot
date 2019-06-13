package com.github.bruce.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "cache.redis")
@Data
public class RedisCacheProperties {

    /**
     * Mapping of cacheNames to expire-after-write timeout in seconds
     */
    private Map<String, Long> expires = new HashMap<>();
}
