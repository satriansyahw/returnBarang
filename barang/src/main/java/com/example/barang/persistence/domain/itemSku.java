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
@Entity(name = "itemSku")
public class itemSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "itemId")
    private int itemId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "qty")
    private double qty;

    @Column(name = "currentQty")
    private double currentQty;

    @Column(name = "price")
    private double price;
}
