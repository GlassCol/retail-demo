package com.retail.demo.controller;

import com.retail.demo.dao.OrderedItemDao;
import com.retail.demo.model.OrderedItem;
import com.retail.demo.service.OrderedItemService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/OrderedItemApi/v1")
public class OrderedItemController {
    private OrderedItemDao orderedItemDao;

    public OrderedItemController(OrderedItemDao orderedItemDao){
        this.orderedItemDao = orderedItemDao;
    }

    @Autowired
    private OrderedItemService orderedItemService;

    @PostMapping("/orderedItems")
    public OrderedItem createOrderedItem(@RequestBody OrderedItem orderedItem){
        return orderedItemService.createOrderedItem(orderedItem);
    }

    @GetMapping("/orderedItems/{orderedItemId}")
    public OrderedItem getOrderedItem(@PathVariable Long orderedItemId){
        return orderedItemService.getOrderedItem(orderedItemId);
    }

    @PutMapping("/orderedItems/{orderedItemId}")
    public OrderedItem updateOrderedItem(@PathVariable Long orderedItemId, @RequestBody OrderedItem orderedItem){
        return orderedItemService.updateOrderedItem(orderedItemId, orderedItem);
    }

    @DeleteMapping("/orderedItems/{orderedItemId}")
    public void deleteOrderedItem(@PathVariable Long orderedItemId){
        orderedItemService.deleteOrderedItem(orderedItemId);
    }

    @GetMapping("/orderedItems/order/{orderId}")
    public List<OrderedItem> getAllOrderedItemsFromOrder(@PathVariable Long orderId){
        return orderedItemService.getAllOrderedItemsFromOrder(orderId);
    }

    @GetMapping("/orderedItems")
    public List<OrderedItem> getAllOrderedItems(){
        return orderedItemService.getAllOrderedItems();
    }
}
