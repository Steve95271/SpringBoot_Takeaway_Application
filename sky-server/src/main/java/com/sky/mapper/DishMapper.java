package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;

import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新菜品
     * @param dish dish对象
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类ID获取菜品
     * @param categoryId
     * @return
     */
    @Select("SELECT id, name, category_id, price, image, description, status, create_time, update_time, create_user, update_user " +
            "FROM dish " +
            "WHERE category_id = #{categoryId}")
    List<Dish> getDishByCategoryId(Long categoryId);

    /**
     * 根据分类ID和起售状态查询菜品
     * @param dish
     * @return
     */
    @Select("SELECT id, name, category_id, price, image, description, status, create_time, update_time, create_user, update_user FROM dish WHERE category_id = #{categoryId} AND status = #{status}")
    List<Dish> list(Dish dish);

    /**
     * 更改菜品的状态（起售/停售）
     * @param status
     * @param id
     */
    @Select("UPDATE dish SET status = #{status} WHERE id = #{id}")
    void changeStatus(Integer status, Long id);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
