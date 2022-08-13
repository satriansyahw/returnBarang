package com.example.barang.persistence.repository;

import com.example.barang.persistence.domain.ReturnsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnsDetailRepository extends JpaRepository<ReturnsDetail,Integer>, JpaSpecificationExecutor<ReturnsDetail> {
    ReturnsDetail findByIdAndReturnsId(Integer id, Integer returnsId);
}
