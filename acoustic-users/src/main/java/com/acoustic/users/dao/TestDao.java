package com.acoustic.users.dao;

import com.acoustic.users.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao extends JpaRepository<TestEntity, Integer> {
}