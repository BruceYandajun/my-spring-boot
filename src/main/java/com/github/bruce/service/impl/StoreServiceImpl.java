package com.github.bruce.service.impl;

import com.github.bruce.model.Book;
import com.github.bruce.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreServiceImpl implements IStoreService {

    @Override
    @Cacheable(value = "storeInfo", key = "#id", cacheManager = "redisCacheManager")
    public Book store (Integer id) {
        log.info("store id : " + id);
        Book book = new Book();
        book.setId(id);
        book.setName("store" + book.getId());
        book.setExpire(5);
        return book;
    }
}
