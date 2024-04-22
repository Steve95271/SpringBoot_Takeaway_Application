package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetMealMapper setMealMapper;

    /**
     * 添加菜品或套餐到购物车
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        //复制数据到ShoppingCart对象
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);

        //把当前用户的id赋值到shoppingCart对象
        //这样查询数据库的时候只会查询到当前用户自己的购物车
        shoppingCart.setUserId(BaseContext.getCurrentId());

        //判断当前物品是否已在购物车中
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);

        if (shoppingCartList != null && shoppingCartList.size() > 0) {
            //如果存在，数量加一
            ShoppingCart shoppingCartItem = shoppingCartList.get(0);
            shoppingCartItem.setNumber(shoppingCartItem.getNumber() + 1); //数量加一
            shoppingCartMapper.updateItemNumber(shoppingCartItem);
        } else {
            //不存在，则要插入记录，数量为1

            //先判断传过来的是菜品还是套餐
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null) {
                //如果dishId不为空，那么用户添加的是菜品，执行添加菜品逻辑

                //查询当前的菜品
                Dish dish = dishMapper.getById(dishId);
                //设置ShoppingCart对象的属性
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                //如果dishId为空，那么传过来的就是套餐
                Setmeal setmeal = setMealMapper.getById(shoppingCartDTO.getSetmealId());
                //设置ShoppingCart对象的属性
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
    }
}
