package com.retail.demo.service;

import com.retail.demo.model.InventoryItem;

import java.util.List;

public interface InventoryItemService {
    InventoryItem createInventoryItem(InventoryItem inventoryItem);
    InventoryItem updateInventoryItem(Long id, InventoryItem inventoryItem);
    InventoryItem getInventoryItem(Long id);
    boolean deleteInventoryItem(Long id);
    List<InventoryItem> getAllInventoryItems();
}
