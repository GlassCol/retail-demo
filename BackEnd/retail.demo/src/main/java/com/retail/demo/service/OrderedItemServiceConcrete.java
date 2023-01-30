package com.retail.demo.service;

import com.retail.demo.dao.OrderedItemDao;
import com.retail.demo.entity.OrderedItemEntity;
import com.retail.demo.model.OrderedItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedItemServiceConcrete implements OrderedItemService{
    private final OrderedItemDao orderedItemDao;
    
    public OrderedItemServiceConcrete(OrderedItemDao orderedItemDao) {
        this.orderedItemDao = orderedItemDao;
    }
    
    @Override
    public OrderedItem createOrderedItem(OrderedItem orderedItem) {
        OrderedItemEntity orderedItemEntity = new OrderedItemEntity();
        BeanUtils.copyProperties(orderedItem, orderedItemEntity);
        orderedItemDao.save(orderedItemEntity);
        return orderedItem;
    }

    @Override
    public OrderedItem updateOrderedItem(Long id, OrderedItem orderedItem) {
        OrderedItemEntity orderedItemEntity = orderedItemDao.findById(id).get();
        orderedItemEntity.setName(orderedItem.getName());
        orderedItemEntity.setDescription(orderedItem.getDescription());
        orderedItemEntity.setPrice(orderedItem.getPrice());
        orderedItemEntity.setQuantity(orderedItem.getQuantity());
        orderedItemEntity.setDiscount(orderedItem.getDiscount());
        orderedItemEntity.setImage(orderedItem.getImage());
        orderedItemEntity.setOrderId(orderedItem.getOrderId());
        orderedItemDao.save(orderedItemEntity);
        return orderedItem;
    }

    @Override
    public OrderedItem getOrderedItem(Long id) {
        OrderedItemEntity orderedItemEntity = orderedItemDao.findById(id).get();
        OrderedItem orderedItem = new OrderedItem();
        BeanUtils.copyProperties(orderedItemEntity, orderedItem);
        return orderedItem;
    }

    @Override
    public boolean deleteOrderedItem(Long id) {
        OrderedItemEntity item = orderedItemDao.findById(id).get();
        orderedItemDao.delete(item);
        return true;
    }

    @Override
    public List<OrderedItem> getAllOrderedItemsFromOrder(Long orderId) {
        List<OrderedItemEntity> orderedItemEntityList = orderedItemDao.findAll();
        List<OrderedItem> orderedItemList = new ArrayList<>();
        for (OrderedItemEntity orderedItemEntity : orderedItemEntityList) {
            if (orderedItemEntity.getOrderId().equals(orderId)) {
                OrderedItem orderedItem = new OrderedItem();
                BeanUtils.copyProperties(orderedItemEntity, orderedItem);
                orderedItemList.add(orderedItem);
            }
        }
        return orderedItemList;
    }

    @Override
    public List<OrderedItem> getAllOrderedItems() {
        List<OrderedItemEntity> orderedItemEntityList = orderedItemDao.findAll();
        List<OrderedItem> orderedItemList = new ArrayList<>();
        for (OrderedItemEntity orderedItemEntity : orderedItemEntityList) {
            OrderedItem orderedItem = new OrderedItem();
            BeanUtils.copyProperties(orderedItemEntity, orderedItem);
            orderedItemList.add(orderedItem);
        }
        return orderedItemList;
    }

    @Override
    public OrderedItem getLatestAddition() {
        List<OrderedItemEntity> orderedItemEntityList = orderedItemDao.findAll();
        OrderedItemEntity orderedItemEntity = orderedItemEntityList.get(orderedItemEntityList.size() - 1);
        OrderedItem orderedItem = new OrderedItem();
        BeanUtils.copyProperties(orderedItemEntity, orderedItem);
        return orderedItem;
    }

    @Override
    public boolean isDaoEmpty() {
        return orderedItemDao.findAll().isEmpty();
    }
}
