package com.retail.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="inventory_items")
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long name;
    private Long price;
    private Long quantity;
    private Long stock;
}
