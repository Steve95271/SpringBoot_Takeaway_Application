package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 给新增菜品插入口味列表
     * @param dishFlavors
     */
    void insertDishFlavors(List<DishFlavor> dishFlavors);

    /**
     * 根据菜品id集合批量删除菜品
     * @param ids id集合
     */
    void deleteByDishIds(List<Long> ids);

    /**
     * 删除单个菜品的口味
     * @param dishId 菜品id
     */
    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品id查询菜品的口味
     * @param dishId dishId
     * @return 菜品对应的口味列表
     */
    @Select("SELECT * FROM dish_flavor WHERE dish_id = #{dishId}")
    List<DishFlavor> getDishFlavorByDishId(Long dishId);

//    /**
//     * 更新菜品的口味
//     * @param dishFlavors 菜品口味集合
//     */
//    void update(List<DishFlavor> dishFlavors);
}
