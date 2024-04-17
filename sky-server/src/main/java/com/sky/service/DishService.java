package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 添加新菜品
     *
     */
    void addDish (DishDTO dishDTO);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return 前端数据模型
     */
    PageResult getDishPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除多个菜品
     * @param ids 菜品的ID
     */
    void deleteDishByIds(List<Long> ids);

    /**
     * 根据id获得菜品
     * @param id 菜品ID
     * @return DishVO对象
     */
    DishVO getDishWithFlavorById(Long id);

    /**
     * 修改菜品信息
     * @param dishDTO 菜品数据传输对象
     */
    void updateDish(DishDTO dishDTO);

    /**
     * 根据分类ID获取菜品
     * @param categoryId
     * @return
     */
    List<Dish> getDishByCategoryId(Long categoryId);
}
