package com.onlineOrder.service;

import com.onlineOrder.dto.ShoppingCartDTO;
import com.onlineOrder.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {


    /**
     * 添加菜品或套餐到购物车
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 查看购物车
     * @return
     */
    List<ShoppingCart> showShoppingCart();

}
