package com.example.barang.persistence.repository;

import com.example.barang.persistence.domain.ItemsSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemSkuRepository extends JpaRepository<ItemsSku,Integer>, JpaSpecificationExecutor<ItemsSku> {

    List<ItemsSku> findDistinctBySku(String sku);
}
