package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 获取当前用户的购物车列表
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 更新物品数量
     * @param shoppingCartItem 携带物品数量的ShoppingCart对象
     */
    @Update("UPDATE shopping_cart SET number = #{number} WHERE id = #{id}")
    void updateItemNumber(ShoppingCart shoppingCartItem);

    /**
     * 插入购物车记录
     * @param shoppingCart
     */
    @Insert("INSERT INTO shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            " VALUES (#{name},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{image},#{createTime})")
    void insert(ShoppingCart shoppingCart);
}
