package com.retail.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
    private Long discount;
    private File image;
    private Long orderId;
}
