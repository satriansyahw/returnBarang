package com.example.barang.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponse extends GenericResponse {
    private static final Long serialVersionUID = -5476365987798176537L;
    private Object data;

    public DataResponse(Boolean isSuccess, Object data, String message) {
        super(isSuccess, message);
        this.data = data;
    }
}

