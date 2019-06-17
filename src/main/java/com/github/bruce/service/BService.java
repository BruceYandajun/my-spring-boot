package com.github.bruce.service;

import com.github.bruce.dao.entity.AEntity;
import com.github.bruce.dao.mapper.AEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BService {

    @Autowired
    private AEntityMapper entityMapper;

    @Transactional
    public int insertB() {
        AEntity a = new AEntity();
        a.setC(1);
        a.setD(1);
        int result = entityMapper.insertSelective(a);
        return result;
    }

    @Transactional
    public int insertC() {
        AEntity a = new AEntity();
        a.setC(2);
        a.setD(2);
        int result = entityMapper.insertSelective(a);
        return result/0;
    }

}
