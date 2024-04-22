package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

public interface ShoppingCartService {


    /**
     * 添加菜品或套餐到购物车
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
