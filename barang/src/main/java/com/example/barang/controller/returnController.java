package com.example.barang.controller;

import org.springframework.web.bind.annotation.*;

import static com.example.barang.util.constants.PATH_RETURNS;

@RestController
public class returnController {
    @PostMapping(value = "/pending/returns")
    public String pendingReturns() {
       return "/pending/returns";
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
