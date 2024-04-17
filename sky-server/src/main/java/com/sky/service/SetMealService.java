package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetMealService {

    /**
     * 新增套餐
     * @param setmealDTO 需要添加的套餐
     */
    void addSetMeal(SetmealDTO setmealDTO);

    /**
     * 分页查询菜单
     * @param setmealPageQueryDTO
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
