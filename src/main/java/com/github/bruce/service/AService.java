package com.github.bruce.service;

import com.github.bruce.dao.entity.AEntity;
import com.github.bruce.dao.mapper.AEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AService {

    @Autowired
    private AEntityMapper entityMapper;

    @Autowired
    private BService bService;

//    @Transactional
    public AEntity getById(Integer id) {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return entityMapper.selectById(id);
    }

    public int updateById(Integer id, Integer d) {
        return entityMapper.updateById(id, d);
    }

//    @Transactional
    public int insertA() {
        AEntity a = new AEntity();
        a.setC(0);
        entityMapper.insertSelective(a);
        try {
            bService.insertB();
        } catch (Exception e) {

        }
        bService.insertC();
        return 1;
    }

}
