package com.example.barang.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "orderTrans")
public class OrderTrans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "itemSkuId")
    private int itemSkuId;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "email")
    private String email;

    @Column(name = "qty")
    private double qty;

}
