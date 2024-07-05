package com.onlineOrder.service;

import com.onlineOrder.dto.SetmealDTO;
import com.onlineOrder.dto.SetmealPageQueryDTO;
import com.onlineOrder.entity.Setmeal;
import com.onlineOrder.result.PageResult;
import com.onlineOrder.vo.DishItemVO;
import com.onlineOrder.vo.SetmealVO;

import java.util.List;

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

    /**
     * 批量删除套餐
     * @param ids 前端传过来的套餐id
     */
    void delete(List<Long> ids);

    /**
     * 更新套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * 更改套餐状态
     * @param status
     * @param id
     */
    void changeStatus(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
