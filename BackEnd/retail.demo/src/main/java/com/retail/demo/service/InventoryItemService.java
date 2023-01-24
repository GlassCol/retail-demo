package com.retail.demo.service;

import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.model.InventoryItem;

import java.util.List;

public interface InventoryItemService {
    InventoryItem createInventoryItem(InventoryItem inventoryItem);
    InventoryItem updateInventoryItem(Long id, InventoryItem inventoryItem);
    InventoryItem getInventoryItem(Long id);
    List<InventoryItem> getInventoryItemByName(String name);
    boolean deleteInventoryItem(Long id);
    List<InventoryItem> getAllInventoryItems();
    InventoryItem getLatestAddition();

    boolean isDaoEmpty();
}
