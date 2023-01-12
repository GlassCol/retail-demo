package com.retail.demo.dao;

import com.retail.demo.entity.OrderEntity;
import com.retail.demo.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Long> {
    /**
     * Gets the last element from the jpa
     * @return Order: the last order in the database
     */
    default Order findLastAddition() {
        List<OrderEntity> orderList = this.findAll();
        OrderEntity orderEntity = orderList.get(orderList.size() - 1);
        Order order = new Order();
        BeanUtils.copyProperties(orderEntity, order);
        return order;
    }
}
