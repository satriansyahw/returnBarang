package com.example.barang.dto.response;

import com.example.barang.dto.request.ReturnsReqDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnByIdResDto {
    private Integer returnsId;
    private Double refundAmount;
    private String status;
    private List<ReturnsDetailResDto> detailItem;

}
