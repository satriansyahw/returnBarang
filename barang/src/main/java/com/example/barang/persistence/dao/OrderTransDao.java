package com.example.barang.persistence.dao;

import com.example.barang.persistence.domain.OrderTrans;
import com.example.barang.persistence.repository.OrderTransRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderTransDao {

    private static Logger logger = LogManager.getLogger(OrderTransDao.class);
    @Autowired
    private OrderTransRepository repository;

    public boolean isAuthorizedReturnUser(String orderId,String email){
        List<OrderTrans>  checkOrder = repository.findDistinctByOrderIdAndEmail(orderId,email);
        if(!checkOrder.isEmpty())
            return true;
        else
            return false;
    }
}
