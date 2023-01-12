package com.retail.demo.service;

import com.retail.demo.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    Order getOrder(Long id);
    boolean deleteOrder(Long id);
    List<Order> getAllOrders();

}
