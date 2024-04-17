package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 通过菜品ID获得套餐
     * @param dishIds 菜品ID
     * @return 被加入套餐中的菜品
     */
    List<Long> getSetMealDishIdsByDishIds(List<Long> dishIds);

    /**
     * 给套餐加入菜品
     * @param setmealDishes
     */
    void insertSetMealDishes(List<SetmealDish> setmealDishes);
}
