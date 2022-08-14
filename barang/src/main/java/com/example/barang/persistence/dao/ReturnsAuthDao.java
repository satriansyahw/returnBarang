package com.example.barang.persistence.dao;

import com.example.barang.controller.ReturnController;
import com.example.barang.persistence.domain.ReturnsAuth;
import com.example.barang.persistence.repository.ReturnsAuthRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReturnsAuthDao {
    private static final Logger logger = LogManager.getLogger(ReturnsAuthDao.class);
    @Autowired
    private ReturnsAuthRepository repository;

    public ReturnsAuth Save(ReturnsAuth returnsAuth){
        return repository.saveAndFlush(returnsAuth);
    }

    public  boolean isElligibleToken(String token)
    {
        ReturnsAuth returnsAuth = repository.findByToken(token).orElse(null);
        if (returnsAuth==null) return  false;
        return  true;
    }
}
