package com.retail.demo.service;

import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.model.InventoryItem;
import com.retail.demo.model.UserOrder;

import java.util.List;

public interface OrderService {
    UserOrder createOrder(UserOrder userOrder);
    UserOrder updateOrder(Long id, UserOrder userOrder);
    UserOrder getOrder(Long id);
    boolean deleteOrder(Long id);
    List<UserOrder> getAllOrders();
    InventoryItem addItemToOrder(Long orderId, InventoryItemEntity inventoryItemEntity);
    boolean removeItemFromOrder(Long orderId, InventoryItemEntity inventoryItemEntity);
    List<InventoryItem> getAllItemsInOrder(Long orderId);
    boolean isDaoEmpty();
    UserOrder getLastOrder();

}
