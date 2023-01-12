package com.retail.demo.service;

import com.retail.demo.dao.InventoryItemDao;
import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.model.InventoryItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryItemServiceConcrete implements InventoryItemService {

    private InventoryItemDao inventoryItemDao;

    public InventoryItemServiceConcrete(InventoryItemDao inventoryItemDao) {
        this.inventoryItemDao = inventoryItemDao;
    }

    @Override
    public InventoryItem createInventoryItem(InventoryItem inventoryItem) {
        InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItem, inventoryItemEntity);
        inventoryItemDao.save(inventoryItemEntity);
        return inventoryItem;
    }

    @Override
    public InventoryItem updateInventoryItem(Long id, InventoryItem inventoryItem) {
        InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItem, inventoryItemEntity);
        inventoryItemDao.save(inventoryItemEntity);
        return inventoryItem;
    }

    @Override
    public InventoryItem getInventoryItem(Long id) {
        InventoryItemEntity inventoryItemEntity = inventoryItemDao.findById(id).get();
        InventoryItem inventoryItem = new InventoryItem();
        BeanUtils.copyProperties(inventoryItemEntity, inventoryItem);
        return inventoryItem;
    }

    @Override
    public boolean deleteInventoryItem(Long id) {
        InventoryItemEntity item = inventoryItemDao.findById(id).get();
        inventoryItemDao.delete(item);
        return true;
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItemEntity> inventoryItemEntities = inventoryItemDao.findAll();
        List<InventoryItem> inventoryItems = new ArrayList<>();
        for (InventoryItemEntity inventoryItemEntity : inventoryItemEntities) {
            InventoryItem inventoryItem = new InventoryItem();
            BeanUtils.copyProperties(inventoryItemEntity, inventoryItem);
            inventoryItems.add(inventoryItem);
        }
        return inventoryItems;
    }


}
