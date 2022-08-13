package com.example.barang.persistence.dao;

import com.example.barang.persistence.domain.ReturnsAuth;
import com.example.barang.persistence.repository.ReturnsAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnsAuthDao {
    @Autowired
    private ReturnsAuthRepository repository;

    public ReturnsAuth Save(ReturnsAuth returnsAuth){
        return repository.saveAndFlush(returnsAuth);
    }
}
