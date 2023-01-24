package com.retail.demo.controller;

import com.retail.demo.dao.ShoppingCartDao;
import com.retail.demo.entity.ShoppingCartItemEntity;
import com.retail.demo.model.InventoryItem;
import com.retail.demo.model.ShoppingCartItem;
import com.retail.demo.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ShoppingCartApi/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ShoppingCartController {

    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/shoppingCart")
    public List<ShoppingCartItem> getShoppingCart(){
        return shoppingCartService.getShoppingCart();
    }

    @PostMapping("/shoppingCart")
    public ShoppingCartItem addToCart(@RequestBody InventoryItem inventoryItem){
        //convert an inventory item to a shopping cart item then pass it in
        ShoppingCartItemEntity shoppingCartItemEntity = new ShoppingCartItemEntity();
        BeanUtils.copyProperties(inventoryItem, shoppingCartItemEntity);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        BeanUtils.copyProperties(shoppingCartItemEntity, shoppingCartItem);
        return shoppingCartService.addToCart(shoppingCartItem);
    }

    @DeleteMapping("/shoppingCart/{shoppingCartItemId}")
    public ResponseEntity<Map<String, Boolean>> deleteFromCart(@PathVariable Long shoppingCartItemId){
        boolean deleted = false;
        deleted = shoppingCartService.removeFromCart(shoppingCartItemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/shoppingCart")
    public ResponseEntity<Map<String, Boolean>> deleteAllFromCart(){
        boolean deleted = false;
        deleted = shoppingCartService.clearShoppingCart();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
