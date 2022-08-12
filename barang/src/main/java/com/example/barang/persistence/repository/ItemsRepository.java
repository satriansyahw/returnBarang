package com.example.barang.persistence.repository;

import com.example.barang.persistence.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Integer>, JpaSpecificationExecutor<Items> {
}
