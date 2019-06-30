package com.github.bruce.dao;

import com.github.bruce.dao.entity.AEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADAO extends JpaRepository<AEntity, Integer> {

}
