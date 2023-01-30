package com.retail.demo.service;

import com.retail.demo.model.OrderedItem;

import java.util.List;

public interface OrderedItemService {
    OrderedItem createOrderedItem(OrderedItem orderedItem);
    OrderedItem updateOrderedItem(Long id, OrderedItem orderedItem);
    OrderedItem getOrderedItem(Long id);
    boolean deleteOrderedItem(Long id);
    List<OrderedItem> getAllOrderedItemsFromOrder(Long orderId);
    List<OrderedItem> getAllOrderedItems();
    OrderedItem getLatestAddition();
    boolean isDaoEmpty();
}
