package com.retail.demo.dao;

import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.model.InventoryItem;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemDao extends JpaRepository<InventoryItemEntity, Long> {
    /**
     * Gets the last element from the jpa
     * @return InventoryItem: the last inventory item in the database
     */

    default InventoryItem findLastAddition() {
        List<InventoryItemEntity> inventoryItemList = this.findAll();
        InventoryItemEntity inventoryItemEntity = inventoryItemList.get(inventoryItemList.size() - 1);
        InventoryItem inventoryItem = new InventoryItem();
        BeanUtils.copyProperties(inventoryItemEntity, inventoryItem);
        return inventoryItem;
    }
}
