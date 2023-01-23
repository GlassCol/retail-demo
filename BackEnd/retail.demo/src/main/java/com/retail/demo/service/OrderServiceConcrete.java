package com.retail.demo.service;

import com.retail.demo.dao.OrderDao;
import com.retail.demo.dao.OrderedItemDao;
import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.entity.OrderEntity;
import com.retail.demo.model.InventoryItem;
import com.retail.demo.model.UserOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceConcrete implements OrderService {

    private OrderDao orderDao;
    private OrderedItemDao orderedItemDao;

    private InventoryItemService inventoryItemService;

    public OrderServiceConcrete(OrderDao orderDao, OrderedItemDao orderedItemDao) {
        this.orderDao = orderDao;
        this.orderedItemDao = orderedItemDao;
    }

    @Override
    public UserOrder createOrder(UserOrder userOrder) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(userOrder, orderEntity);
        orderDao.save(orderEntity);
        userOrder = orderDao.findLastAddition();
        return userOrder;
    }

    @Override
    public UserOrder updateOrder(Long id, UserOrder userOrder) {
        OrderEntity orderEntity = orderDao.findById(id).get();
        orderEntity.setItemQuantity(userOrder.getItemQuantity());
        orderEntity.setPrice(userOrder.getPrice());
        orderEntity.setDiscount(userOrder.getDiscount());
        orderEntity.setTotal(userOrder.getTotal());
        orderEntity.setTax(userOrder.getTax());
        orderEntity.setStatus(userOrder.getStatus());
        orderDao.save(orderEntity);
        return userOrder;
    }

    @Override
    public UserOrder getOrder(Long id) {
        OrderEntity orderEntity = orderDao.findById(id).get();
        UserOrder userOrder = new UserOrder();
        BeanUtils.copyProperties(orderEntity, userOrder);
        return userOrder;
    }

    @Override
    public boolean deleteOrder(Long id) {
        //get rid of all items in the order from the ordered items dao first
        for (InventoryItem item : getAllItemsInOrder(id)){
            InventoryItemEntity itemEntity = new InventoryItemEntity();
            BeanUtils.copyProperties(item, itemEntity);
            removeItemFromOrder(id, itemEntity);
        }
        OrderEntity order = orderDao.findById(id).get();
        orderDao.delete(order);
        return true;
    }

    @Override
    public List<UserOrder> getAllOrders() {
        List<OrderEntity> orderEntities = orderDao.findAll();
        return orderEntities.stream()
                .map(order -> new UserOrder(order.getId(), order.getUserId(), order.getItemQuantity(), order.getPrice(),
                        order.getDiscount(), order.getTotal(), order.getTax(), order.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public InventoryItem addItemToOrder(Long orderId, InventoryItemEntity inventoryItemEntity) {
        inventoryItemEntity.setOrderId(orderId);
        orderedItemDao.save(inventoryItemEntity);
        InventoryItem inventoryItem = new InventoryItem();
        BeanUtils.copyProperties(inventoryItemEntity, inventoryItem);
        return inventoryItem;
    }

    @Override
    public boolean removeItemFromOrder(Long orderId, InventoryItemEntity inventoryItemEntity) {
        orderedItemDao.delete(inventoryItemEntity);
        return true;
    }

    @Override
    public List<InventoryItem> getAllItemsInOrder(Long orderId) {
        if (orderedItemDao.findAll().isEmpty() || orderedItemDao == null) {
            return null;
        }
        List<InventoryItemEntity> itemEntities = orderedItemDao.findAll();
        return itemEntities.stream()
                .filter(itemEntity -> itemEntity.getOrderId().equals(orderId))
                .map(itemEntity -> new InventoryItem(itemEntity.getId(), itemEntity.getName(),
                        itemEntity.getDescription(), itemEntity.getPrice(), itemEntity.getQuantity(),
                        itemEntity.getDiscount(), itemEntity.getImage(),
                        itemEntity.getOrderId()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isDaoEmpty() {
        return orderDao.findAll().isEmpty();
    }

    @Override
    public UserOrder getLastOrder() {
        return orderDao.findLastAddition();
    }
}
