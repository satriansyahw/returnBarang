package com.example.barang.persistence.projection;

public interface ReturnsData {
    Integer getReturnId();

    Integer getItemDetailId();
    String getOrderId();
    String getStatus();
    String getSku();
    Integer getQty();
    String getQcStatus();
    double getPrice();

}