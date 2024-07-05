package com.onlineOrder.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlineOrder.dto.SetmealDTO;
import com.onlineOrder.dto.SetmealPageQueryDTO;
import com.onlineOrder.entity.Setmeal;
import com.onlineOrder.entity.SetmealDish;
import com.onlineOrder.mapper.SetMealDishMapper;
import com.onlineOrder.mapper.SetMealMapper;
import com.onlineOrder.result.PageResult;
import com.onlineOrder.service.SetMealService;
import com.onlineOrder.vo.DishItemVO;
import com.onlineOrder.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setmealMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * 新增套餐
     * @param setmealDTO 需要添加的套餐
     */
    @Override
    @Transactional
    public void addSetMeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();

        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmealMapper.addSetMeal(setmeal);

        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();

        if (setmealDishes != null && setmealDishes.size() > 0) {
            for (SetmealDish setmealDish : setmealDishes) {
                setmealDish.setSetmealId(setmealId);
            }

            setMealDishMapper.insertSetMealDishes(setmealDishes);
        }




    }

    /**
     * 分页查询菜单
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Page<Setmeal> setMealDishList = setmealMapper.pageQuery(setmealPageQueryDTO);

        return new PageResult(setMealDishList.getTotal(), setMealDishList.getResult());
    }

    /**
     * 批量删除套餐
     * @param ids 前端传过来的套餐id
     */
    @Override
    @Transactional
    public void delete(List<Long> ids) {
        setmealMapper.delete(ids);
        setMealDishMapper.delete(ids);
    }

    /**
     * 更新套餐
     * @param setmealDTO
     */
    @Override
    @Transactional //保证事务原子性
    public void update(SetmealDTO setmealDTO) {
        //准备好要进行更新的数据
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        if (setmealDishList != null && setmealDishList.size() > 0) {
            for (SetmealDish setmealDish : setmealDishList) {
                setmealDish.setSetmealId(setmeal.getId());
            }
        }
        List<Long> setmealId = new ArrayList<>();
        setmealId.add(setmeal.getId());

        //Sql操作
        setMealDishMapper.delete(setmealId);
        setmealMapper.update(setmeal);
        setMealDishMapper.insertSetMealDishes(setmealDishList);
    }

    /**
     * 根据ID查询套餐
     * @param id
     * @return
     */
    @Override
    public SetmealVO getById(Long id) {
        SetmealVO setmealVO = new SetmealVO();
        Setmeal setmeal = setmealMapper.getById(id);
        List<SetmealDish> setmealDishList = setMealDishMapper.getBySetMealId(id);
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishList);
        return setmealVO;
    }

    /**
     * 套餐起售、停售
     * @param status
     * @param id
     */
    @Override
    public void changeStatus(Integer status, Long id) {
        setmealMapper.changeStatus(status, id);
    }

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }

}
