package com.github.bruce.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.bruce.service.rpc.UserRpcService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Used local redis cache
 *
 * The steps starting redis-server below:
 * First step : open redis root path '/Users/yandajun/software/develop/redis-4.0.8'
 * Second step : execute bin file 'src/redis-server'
 */
@Configuration
@EnableCaching
public class CachingConfig {
    /**
     * Default cacheManager, expire after write 5 seconds, no limit of maximum size
     */
    @Primary
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS));
        return cacheManager;
    }

    /**
     * Particular cacheManager, expire after last access 5 seconds,  10 maximum size
     */
    @Bean
    public CacheManager particularCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(10).expireAfterAccess(5, TimeUnit.SECONDS));
        return cacheManager;
    }

    /**
     * This cache manager will refresh cache per 5 seconds,
     * if other cache is added, just append the caches list
     */
    @Bean
    public CacheManager autoRefreshCacheManager(UserRpcService userRpcService) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        LoadingCache<Object, Object> usersCache =
                Caffeine.newBuilder()
                        .maximumSize(1)
                        .refreshAfterWrite(5, TimeUnit.SECONDS)
                        .build(a -> userRpcService.getAllUsers());

        cacheManager.setCaches(Arrays.asList(new CaffeineCache("allUsers", usersCache)));
        return cacheManager;
    }

    @Bean
    public CacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(60);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

}
