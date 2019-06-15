package com.github.bruce.service.impl;

import com.github.bruce.service.IFirstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description
 * <p>
 * </p>
 * DATE 12/18/17.
 *
 * @author yandajun.
 */
@Service
public class FirstServiceImpl implements IFirstService {

    private final Logger logger = LoggerFactory.getLogger(FirstServiceImpl.class);

    @Override
    public void service() {
        logger.info("enter service method");
    }
}
