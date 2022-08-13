package com.example.barang.persistence.dao;

import com.example.barang.persistence.domain.ReturnsDetail;
import com.example.barang.persistence.repository.ReturnsDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReturnsDetailDao {
    @Autowired
    private ReturnsDetailRepository repository;

    public  ReturnsDetail saveOrUpdate(ReturnsDetail returnsDetail)
    {
        return repository.save(returnsDetail);
    }

    public  List<ReturnsDetail> saveBulk(List<ReturnsDetail> returnsDetails)
    {
        return  repository.saveAll(returnsDetails);
    }
}
