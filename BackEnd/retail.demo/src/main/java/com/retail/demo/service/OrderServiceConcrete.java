package com.retail.demo.service;

import com.retail.demo.dao.OrderDao;
import com.retail.demo.dao.OrderedItemDao;
import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.entity.OrderEntity;
import com.retail.demo.entity.OrderedItemEntity;
import com.retail.demo.model.OrderedItem;
import com.retail.demo.model.ShoppingCartItem;
import com.retail.demo.model.User;
import com.retail.demo.model.UserOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceConcrete implements OrderService {

    private OrderDao orderDao;
    private OrderedItemDao orderedItemDao;
    @Autowired
    private ShoppingCartService shoppingCartService;

    private UserOrder currentUserOrder;

    public OrderServiceConcrete(OrderDao orderDao, OrderedItemDao orderedItemDao) {
        this.orderDao = orderDao;
        this.orderedItemDao = orderedItemDao;
    }

    @Override
    public UserOrder createOrder(UserOrder userOrder) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(userOrder, orderEntity);
        orderEntity.setTotal(userOrder.getPrice() + userOrder.getTax() - userOrder.getDiscount());
        orderDao.save(orderEntity);
        userOrder = orderDao.findLastAddition();
        if (saveShoppingCartItemsInOrder(userOrder)) {
            return userOrder;
        } else {
            return null;
        }
    }

    private boolean saveShoppingCartItemsInOrder(UserOrder userOrder) {
        try {
            for (ShoppingCartItem shoppingCartItem : shoppingCartService.getShoppingCart()) {
                OrderedItemEntity orderedItem = new OrderedItemEntity();
                BeanUtils.copyProperties(shoppingCartItem, orderedItem);
                orderedItem.setOrderId(userOrder.getId());
                orderedItemDao.save(orderedItem);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
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
    public List<UserOrder> getOrdersByUserId(Long id) {
        List<OrderEntity> orderEntityList = orderDao.findAll();
        List<OrderEntity> orderEntityListByUserId = orderEntityList.stream()
                .filter(orderEntity -> orderEntity.getUserId().equals(id))
                .collect(Collectors.toList());
        List<UserOrder> orderList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityListByUserId) {
            UserOrder userOrder = new UserOrder();
            BeanUtils.copyProperties(orderEntity, userOrder);
            orderList.add(userOrder);
        }
        return orderList;
    }

    @Override
    public boolean deleteOrder(Long id) {
        //get rid of all items in the order from the ordered items dao first
        for (OrderedItem item : getAllItemsInOrder(id)) {
            OrderedItemEntity itemEntity = new OrderedItemEntity();
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
    public OrderedItem addItemToOrder(Long orderId, InventoryItemEntity inventoryItemEntity) {
        OrderedItemEntity orderedItemEntity = new OrderedItemEntity();
        BeanUtils.copyProperties(inventoryItemEntity, orderedItemEntity);
        orderedItemEntity.setOrderId(orderId);
        orderedItemDao.save(orderedItemEntity);
        OrderedItem orderedItem = new OrderedItem();
        BeanUtils.copyProperties(orderedItemEntity, orderedItem);
        return orderedItem;
    }

    @Override
    public boolean removeItemFromOrder(Long orderId, OrderedItemEntity orderedItemEntity) {
        orderedItemDao.delete(orderedItemEntity);
        return true;
    }

    @Override
    public List<OrderedItem> getAllItemsInOrder(Long orderId) {
        if (orderedItemDao.findAll().isEmpty() || orderedItemDao == null) {
            return null;
        }
        List<OrderedItemEntity> itemEntities = orderedItemDao.findAll();
        return itemEntities.stream()
                .filter(itemEntity -> itemEntity.getOrderId().equals(orderId))
                .map(itemEntity -> new OrderedItem(itemEntity.getId(), itemEntity.getName(),
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

    @Override
    public UserOrder setViewingOrder(UserOrder userOrder) {
        this.currentUserOrder = userOrder;
        return this.currentUserOrder;
    }

    @Override
    public UserOrder getViewingOrder() {
        return this.currentUserOrder;
    }
}
