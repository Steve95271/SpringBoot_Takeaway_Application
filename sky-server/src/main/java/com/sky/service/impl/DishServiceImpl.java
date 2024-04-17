package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * 添加新菜品
     *
     */
    @Override
    @Transactional
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        //拷贝对应属性到dish对象
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        //获取insert语句生成的dish id
        Long dishId = dish.getId();

        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        if (dishFlavors != null && dishFlavors.size() > 0) {
            for (DishFlavor dishFlavor : dishFlavors) {
                dishFlavor.setDishId(dishId);
            }
        }

        dishFlavorMapper.insertDishFlavors(dishFlavors);

    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return 前端数据模型
     */
    @Override
    public PageResult getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        //使用插件进行分页查询
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<DishVO> dishVOS = dishMapper.pageQuery(dishPageQueryDTO);

        long total = dishVOS.getTotal();
        List<DishVO> result = dishVOS.getResult();

        return new PageResult(total, result);
    }

    /**
     * 批量删除多个菜品
     * @param ids 菜品的ID
     */
    @Override
    @Transactional
    public void deleteDishByIds(List<Long> ids) {
        //判断当前菜品是否能够删除---是否存在起售中的菜品？
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            //如果传进来的id集合有一个显示菜品起首中，抛异常
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //判断当前菜品是否能够删除---是否被套餐关联？如果被关联，则不能删除。
        List<Long> setMealIds = setMealDishMapper.getSetMealDishIdsByDishIds(ids);
        if (setMealIds != null && setMealIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //删除菜品表中的菜品数据
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);
    }

    /**
     * 根据id获得菜品
     * @param id 菜品ID
     * @return DishVO对象
     */
    @Override
    public DishVO getDishWithFlavorById(Long id) {
        DishVO dishVO = new DishVO();

        Dish dish = dishMapper.getById(id);
        dishVO.setFlavors(dishFlavorMapper.getDishFlavorByDishId(id));

        BeanUtils.copyProperties(dish, dishVO);

        return dishVO;
    }

    /**
     * 修改菜品信息
     * @param dishDTO 菜品数据传输对象
     */
    @Override
    @Transactional
    public void updateDish(DishDTO dishDTO) {
        //准备好要进行sql操作的数据
        Dish dish = new Dish();
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        BeanUtils.copyProperties(dishDTO, dish);

        //给dish表更新
        dishMapper.update(dish);

        //先删除原本的口味
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        //给dish_flavor表更新
        if (dishFlavors != null && dishFlavors.size() > 0) {
            dishFlavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });

            dishFlavorMapper.insertDishFlavors(dishFlavors);
        }
    }

    /**
     * 根据分类ID获取菜品
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> getDishByCategoryId(Long categoryId) {
        return dishMapper.getDishByCategoryId(categoryId);
    }
}
