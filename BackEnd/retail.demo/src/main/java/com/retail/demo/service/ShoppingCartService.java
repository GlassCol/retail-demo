package com.retail.demo.service;

import com.retail.demo.model.InventoryItem;
import com.retail.demo.model.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartItem addToCart(ShoppingCartItem shoppingCartItem);
    List<ShoppingCartItem> getShoppingCart();
    boolean clearShoppingCart();
    boolean removeFromCart(Long itemId);

    boolean isDaoEmpty();

    ShoppingCartItem getLastItem();
}
