package com.example.barang.persistence.dao;

import com.example.barang.persistence.domain.Items;
import com.example.barang.persistence.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class ItemsDao {
    @Autowired
    private ItemsRepository repository;
    public List<Items> GetAll()
    {
        return repository.findAll();
    }

}
