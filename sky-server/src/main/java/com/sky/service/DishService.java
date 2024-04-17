package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
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
}
