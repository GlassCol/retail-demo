package com.retail.demo.service;

import com.retail.demo.dao.OrderDao;
import com.retail.demo.entity.OrderEntity;
import com.retail.demo.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceConcrete implements OrderService {

    private OrderDao orderDao;
    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        orderDao.save(orderEntity);
        order = orderDao.findLastAddition();
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        orderDao.save(orderEntity);
        return order;
    }

    @Override
    public Order getOrder(Long id) {
        OrderEntity orderEntity = orderDao.findById(id).get();
        Order order = new Order();
        BeanUtils.copyProperties(orderEntity, order);
        return order;
    }

    @Override
    public boolean deleteOrder(Long id) {
        OrderEntity order = orderDao.findById(id).get();
        orderDao.delete(order);
        return true;
    }

    @Override
    public List<Order> getAllOrders() {
        List<OrderEntity> orderEntities = orderDao.findAll();
        List<Order> orders = orderEntities.stream()
                .map(order -> new Order(order.getId(), order.getUserId(), order.getItemQuantity(), order.getPrice(),
                        order.getDiscount(),order.getTotal(),order.getTax(), order.getStatus()))
                .collect(Collectors.toList());
        return orders;
    }
}
