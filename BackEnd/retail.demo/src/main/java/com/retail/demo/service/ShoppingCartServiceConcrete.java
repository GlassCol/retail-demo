package com.retail.demo.service;

import com.retail.demo.dao.ShoppingCartDao;
import com.retail.demo.entity.ShoppingCartItemEntity;
import com.retail.demo.model.ShoppingCartItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceConcrete implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;

    public ShoppingCartServiceConcrete(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public ShoppingCartItem addToCart(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItemEntity shoppingCartItemEntity = new ShoppingCartItemEntity();
        BeanUtils.copyProperties(shoppingCartItem, shoppingCartItemEntity);
        shoppingCartDao.save(shoppingCartItemEntity);
        return shoppingCartItem;
    }

    @Override
    public List<ShoppingCartItem> getShoppingCart() {
        if(shoppingCartDao.findAll().isEmpty()) {
            return null;
        }
        List<ShoppingCartItemEntity> shoppingCartItemEntityList = shoppingCartDao.findAll();
        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
        for (ShoppingCartItemEntity shoppingCartItemEntity : shoppingCartItemEntityList) {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            BeanUtils.copyProperties(shoppingCartItemEntity, shoppingCartItem);
            shoppingCartItemList.add(shoppingCartItem);
        }
        return shoppingCartItemList;
    }

    @Override
    public boolean clearShoppingCart() {
        if(shoppingCartDao.findAll().isEmpty()) {
            return false;
        }
        shoppingCartDao.deleteAll();
        return true;
    }

    @Override
    public boolean removeFromCart(Long itemId) {
        ShoppingCartItemEntity item = shoppingCartDao.findById(itemId).get();
        shoppingCartDao.delete(item);
        return true;
    }

    @Override
    public boolean isDaoEmpty() {
        return shoppingCartDao.findAll().isEmpty();
    }

    @Override
    public ShoppingCartItem getLastItem() {
        List<ShoppingCartItemEntity> shoppingCartItemEntityList = shoppingCartDao.findAll();
        ShoppingCartItemEntity shoppingCartItemEntity = shoppingCartItemEntityList.get(shoppingCartItemEntityList.size() - 1);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        BeanUtils.copyProperties(shoppingCartItemEntity, shoppingCartItem);
        return shoppingCartItem;
    }
}
