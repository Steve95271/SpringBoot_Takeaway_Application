package com.sky.service;

import com.sky.dto.SetmealDTO;

public interface SetMealService {

    /**
     * 新增套餐
     * @param setmealDTO 需要添加的套餐
     */
    void addSetMeal(SetmealDTO setmealDTO);

}
