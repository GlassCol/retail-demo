package com.retail.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.File;

@Entity
@Data
@Table(name="inventory_items")
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
    private Long discount;
    private File image;
    private Long orderId;
}
