package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 给新增菜品插入口味列表
     * @param dishFlavors
     */
    void insertDishFlavors(List<DishFlavor> dishFlavors);
}
