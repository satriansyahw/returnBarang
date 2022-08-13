package com.example.barang.controller;

import com.example.barang.dto.request.PendingReturnsReqDto;
import com.example.barang.dto.request.ReturnsReqDto;
import com.example.barang.service.OrderTransService;
import com.example.barang.service.ReturnsService;
import com.example.barang.util.DataResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReturnController {
    private static final Logger logger = LogManager.getLogger(ReturnController.class);
    @Autowired
    OrderTransService orderTransService;

    @Autowired
    ReturnsService returnsService;

    @PostMapping(value = "/pending/returns")
    public DataResponse pendingReturns(@RequestBody PendingReturnsReqDto returnsReqDto) {
        return orderTransService.pendingReturnsAuth((returnsReqDto));
    }
    @PostMapping(value = "returns")
    public DataResponse returns(@RequestBody List<ReturnsReqDto> returnsReqDtos ) {

        return returnsService.creatingReturns(returnsReqDtos);
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
