package com.retail.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem {
    private Long id;
    private Long name;
    private Long price;
    private Long quantity;
    private Long stock;
}
