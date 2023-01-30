package com.retail.demo.dao;

import com.retail.demo.entity.OrderedItemEntity;
import com.retail.demo.model.OrderedItem;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderedItemDao extends JpaRepository<OrderedItemEntity, Long> {
    /**
     * Gets the last element from the jpa
     * @return orderedItem: the last ordered item in the database
     */
    default OrderedItem findLastAddition() {
        List<OrderedItemEntity> orderedItemList = this.findAll();
        OrderedItemEntity orderedItemEntity = orderedItemList.get(orderedItemList.size() - 1);
        OrderedItem orderedItem = new OrderedItem();
        BeanUtils.copyProperties(orderedItemEntity, orderedItem);
        return orderedItem;
    }
}
