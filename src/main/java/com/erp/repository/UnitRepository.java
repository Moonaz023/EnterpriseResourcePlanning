package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.UnitEntity;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {

}
