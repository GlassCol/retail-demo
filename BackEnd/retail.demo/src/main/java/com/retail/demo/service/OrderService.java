package com.retail.demo.service;

import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.entity.OrderedItemEntity;
import com.retail.demo.model.OrderedItem;
import com.retail.demo.model.UserOrder;

import java.util.List;

public interface OrderService {
    UserOrder createOrder(UserOrder userOrder);
    UserOrder updateOrder(Long id, UserOrder userOrder);
    UserOrder getOrder(Long id);
    List<UserOrder> getOrdersByUserId(Long id);
    boolean deleteOrder(Long id);
    List<UserOrder> getAllOrders();
    OrderedItem addItemToOrder(Long orderId, InventoryItemEntity inventoryItemEntity);
    boolean removeItemFromOrder(Long orderId, OrderedItemEntity inventoryItemEntity);
    List<OrderedItem> getAllItemsInOrder(Long orderId);
    boolean isDaoEmpty();
    UserOrder getLastOrder();

    UserOrder setViewingOrder(UserOrder userOrder);

    UserOrder getViewingOrder();

}
