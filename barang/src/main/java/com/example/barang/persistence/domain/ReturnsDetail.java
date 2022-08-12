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

    @Column(name = "orderReturnId")
    private int orderReturnId;

    @Column(name = "qty")
    private double qty;

    @Column(name = "qcStatus")
    private String qcStatus;
}
