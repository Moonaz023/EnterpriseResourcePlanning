package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.VendorEntity;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

	List<VendorEntity> findByTenantId(long tenantId);
}
