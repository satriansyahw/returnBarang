package com.example.barang.persistence.repository;

import com.example.barang.persistence.domain.ReturnsAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReturnsAuthRepository extends JpaRepository<ReturnsAuth,Integer>, JpaSpecificationExecutor<ReturnsAuth> {
}
