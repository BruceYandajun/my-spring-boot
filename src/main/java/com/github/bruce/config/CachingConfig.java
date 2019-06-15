package com.github.bruce.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.bruce.service.rpc.UserRpcService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;


/**
 * Used local redis cache
 *
 * The steps starting redis-server below:
 * First step : open redis root path '/Users/yandajun/software/develop/redis-4.0.8'
 * Second step : execute bin file 'src/redis-server'
 */
@Configuration
@EnableConfigurationProperties(RedisCacheProperties.class)
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {

    /**
     * Default cacheManager, expire after write 60 seconds, 2000 limit of maximum size
     */
    @Primary
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(2000).expireAfterWrite(60, TimeUnit.SECONDS));
        return cacheManager;
    }

    /**
     * Five-minutes cacheManager, expire after last access 5 seconds, 2000 maximum size
     */
    @Bean
    public CacheManager fiveSecondsCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(2000).expireAfterWrite(5, TimeUnit.SECONDS));
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

    /**
     * Redis cacheManager, the value will expire after 60 seconds after being written
     */
    @Bean
    public CacheManager redisCacheManager(LettuceConnectionFactory redisConnectionFactory, RedisCacheProperties properties) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        Map<String, RedisCacheConfiguration> particularConfigs = new HashMap<>();
        for (Entry<String, Long> cacheNameAndTimeout : properties.getExpires().entrySet()) {
            particularConfigs.put(cacheNameAndTimeout.getKey(),
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(cacheNameAndTimeout.getValue()))
                            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
        }
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultConfig).withInitialCacheConfigurations(particularConfigs).build();
    }

    @Bean
    public RedisTemplate<Serializable, Serializable> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<Serializable, Serializable> template = new RedisTemplate();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}