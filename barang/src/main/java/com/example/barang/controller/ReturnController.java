package com.example.barang.controller;

import com.example.barang.dto.request.PendingReturnsReqDto;
import com.example.barang.dto.request.ReturnsReqDto;
import com.example.barang.service.OrderTransService;
import com.example.barang.service.ReturnsService;
import com.example.barang.util.DataResponse;
import com.example.barang.util.GenericResponse;
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
    public DataResponse pendingReturnsAuth(@RequestBody PendingReturnsReqDto returnsReqDto) {
        return orderTransService.pendingReturnsAuth((returnsReqDto));
    }
    @PostMapping(value = "returns")
    public DataResponse creatingReturns(@RequestBody List<ReturnsReqDto> returnsReqDtos ) {

        return returnsService.creatingReturns(returnsReqDtos);
    }
    @PutMapping(value = "returns/{returnsId}/items/{itemsDetailId}/qc/{status}")
    public GenericResponse updateReturnsDetailByStatusAndItemDetailId(@PathVariable(name = "returnsId") Integer returnsId
            , @PathVariable(name = "itemsDetailId") Integer itemsDetailId
            , @PathVariable(name = "status") String status) {
        return returnsService.updateReturnsDetailByStatusAndItemDetailId(returnsId,itemsDetailId,status);
    }
    @GetMapping("returns/{id}")
    public DataResponse returnsId(@PathVariable(name = "id") Integer id) {
        return returnsService.getReturnsById(id);
    }

    @PutMapping(value = "returns/{returnsId}/status/{status}")
    public GenericResponse returnQCStatus(@PathVariable(name = "returnsId") Integer returnsId
            , @PathVariable(name = "status") String status) {
        return returnsService.updateReturnsByIdAndStatus(returnsId,status);
    }

}
