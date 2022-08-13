package com.example.barang.persistence.repository;

import com.example.barang.persistence.domain.Returns;
import com.example.barang.persistence.projection.ReturnsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnsRepository extends JpaRepository<Returns,Integer>, JpaSpecificationExecutor<Returns> {
@Query(value = " select a.id as returnId,a.order_id as orderId,a.status as status, " +
        " b.sku as sku, b.qty as qty ,b.qc_status as qcStatus " +
        " from returns a inner join returns_detail b on a.id = b.returns_id " +
        " where a.order_id = :orderId and b.sku = :sku",nativeQuery = true
)
    List<ReturnsData> getReturnByOrderIdAndSku(@Param("orderId")String orderId, @Param("sku")String sku);
}
