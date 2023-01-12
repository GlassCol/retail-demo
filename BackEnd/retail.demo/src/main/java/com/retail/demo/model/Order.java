package com.retail.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long itemQuantity;
    private Long price;
    private Long discount;
    private Long total;
    private Long tax;
    private String status;
}
