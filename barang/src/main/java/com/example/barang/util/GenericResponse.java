package com.example.barang.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse implements Serializable {
    private static final Long serialVersionUID = -5986365207798154437L;
    private Boolean isSuccess;
    private String message;
    private String version;

    public GenericResponse(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
