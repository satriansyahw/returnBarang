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
@Entity(name = "returnAuth")
public class ReturnsAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "email")//registration//saving/transfer/debit
    private String email;

    @Column(name = "token")
    private String token;
}
