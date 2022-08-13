package com.example.barang.persistence.dao;

import com.example.barang.persistence.domain.Returns;
import com.example.barang.persistence.projection.ReturnsData;
import com.example.barang.persistence.repository.ReturnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReturnsDao {
    @Autowired
    private ReturnsRepository repository;

    public Returns saveOrUpdate(Returns returns){
        return repository.save(returns);
    }

    public List<ReturnsData> getReturnByOrderIdAndSku(String orderId,String sku){
        return  repository.getReturnByOrderIdAndSku(orderId,sku);
    }
}
