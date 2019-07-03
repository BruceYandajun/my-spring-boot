package com.github.bruce.dao.repository;

import com.github.bruce.dao.entity.AEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ARepository extends JpaRepository<AEntity, Integer> {

}
