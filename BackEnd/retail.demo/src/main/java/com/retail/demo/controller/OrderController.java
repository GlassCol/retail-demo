package com.retail.demo.controller;

import com.retail.demo.dao.OrderDao;
import com.retail.demo.model.UserOrder;
import com.retail.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/OrderApi/v1")
public class OrderController {
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public void createOrder(@RequestBody UserOrder userOrder){
        orderService.createOrder(userOrder);
    }

    @GetMapping("/orders/{orderId}")
    public UserOrder getOrder(@PathVariable Long orderId){
        return orderService.getOrder(orderId);
    }

    @GetMapping("/orders/viewOrder")
    public UserOrder getViewingOrder(){
        return orderService.getViewingOrder();
    }

    @PostMapping("/orders/viewOrder")
    public UserOrder setViewingOrder(@RequestBody UserOrder userOrder){
        return orderService.setViewingOrder(userOrder);
    }

    @GetMapping("/orders/user/{userId}")
    public List<UserOrder> getUserOrders(@PathVariable Long userId){
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<UserOrder> updateOrder(@PathVariable Long orderId,
                                                 @RequestBody UserOrder userOrder){
        orderService.updateOrder(orderId, userOrder);
        return ResponseEntity.ok(userOrder);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long orderId){
        boolean deleted = false;
        deleted = orderService.deleteOrder(orderId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
