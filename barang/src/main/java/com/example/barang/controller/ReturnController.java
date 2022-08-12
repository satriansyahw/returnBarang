package com.example.barang.controller;

import com.example.barang.persistence.dao.ItemsDao;
import com.example.barang.persistence.domain.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReturnController {
    @Autowired
    ItemsDao itemDao;
    @PostMapping(value = "/pending/returns")
    public List<Items> pendingReturns() {

       return itemDao.GetAll();
    }
    @PostMapping(value = "returns")
    public String returns() {
        return "returs";
    }
    @PutMapping(value = "returns/{id}/items/{itemsId}/qc/status")
    public String returnQCStatus(@PathVariable(name = "id") String id,@PathVariable(name = "itemsId") String itemsId) {
        return "returns/{id}/items/{itemsId}/qc/status__"+id+"___"+itemsId;
    }
    @GetMapping("returns/{id}")
    public String returnsId(@PathVariable(name = "id") String id) {
        return "returnsId__"+id;
    }



}