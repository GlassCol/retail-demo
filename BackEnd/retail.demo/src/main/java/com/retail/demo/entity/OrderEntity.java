package com.retail.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long itemQuantity;
    private Long price;
    private Long discount;
    private Long total;
    private Long tax;
    private String status;
}
