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
@Entity(name = "returnsDetail")
public class ReturnsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "returnsId")
    private int returnsId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "qcStatus")
    private String qcStatus;

    @Column(name = "price")
    private double price;
}
