package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 批量删除多个套餐的菜品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据套餐id查询菜品
     * @param setmealId
     * @return
     */
    @Select("SELECT * FROM setmeal_dish WHERE setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetMealId(Long setmealId);
}
