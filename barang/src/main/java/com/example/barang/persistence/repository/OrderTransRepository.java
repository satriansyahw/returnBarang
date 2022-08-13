package com.example.barang.persistence.repository;

import com.example.barang.persistence.domain.Items;
import com.example.barang.persistence.domain.OrderTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderTransRepository extends JpaRepository<OrderTrans,Integer>, JpaSpecificationExecutor<OrderTrans> {
    List<OrderTrans> findByOrderIdAndEmail(String orderId, String Email);
    List<OrderTrans> findByOrderIdAndItemSkuId(String orderId,Integer itemSkuId);
}
