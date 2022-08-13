package com.example.barang.persistence.dao;

import com.example.barang.persistence.domain.ItemsSku;
import com.example.barang.persistence.repository.ItemSkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemsSkuDao {
    @Autowired
    private ItemSkuRepository repository;

    public Integer getItemSkuIdBySku (String sku){
        List<ItemsSku> checkOrder = repository.findDistinctBySku(sku);
        Integer itemSkuId=0;
        if(!checkOrder.isEmpty())
        {
            itemSkuId = checkOrder.get(0).getId();
            return  itemSkuId;
        }
        return  itemSkuId;
    }
    public ItemsSku getItemSkuBySku (String sku){
        List<ItemsSku> listDataSku = repository.findDistinctBySku(sku);
        if(!listDataSku.isEmpty())
        return  listDataSku.get(0);
        else return  null;
    }
}
