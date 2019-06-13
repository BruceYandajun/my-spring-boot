package com.github.bruce.service;

import com.github.bruce.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StoreService {

    @Cacheable(value = "storeInfo", key = "#id", cacheManager = "redisCacheManager")
    public Book store(Integer id) {
        log.info("store id : " + id);
        Book book = new Book();
        book.setId(id);
        book.setName("store" + book.getId());
        book.setExpire(5);
        return book;
    }

}
