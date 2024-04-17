package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);


    /**
     * 新增菜品
     * @param dish 赋值好的菜品对象
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);


    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据ID查询菜品
     * @param id 菜品的ID
     * @return 被查到的菜品
     */
    @Select("SELECT id, name, category_id, price, image, description, status, create_time, update_time, create_user, update_user " +
            "FROM dish " +
            "WHERE id = #{id}")
    Dish getById(Long id);

    /**
     * 根据多个id进行批量删除
     * @param ids id集合
     */
    void deleteByIds(List<Long> ids);
}
