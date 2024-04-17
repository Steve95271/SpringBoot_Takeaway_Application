package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO 菜品数据传输对象
     * @return 成功的响应信息
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result addDish(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品: {}", dishDTO);
        dishService.addDish(dishDTO);
        return Result.success();
    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO 分页查询菜品的对象
     * @return 分页查询结果
     */
    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        PageResult pageResult = dishService.getDishPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除多个菜品
     * @param ids 菜品的ID
     */
    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result deleteDish(@RequestParam List<Long> ids) { //@RequestParam注解可以把前端传过来的多个id转换成List集合
        log.info("批量删除多个菜品，id 为: {}", ids);
        dishService.deleteDishByIds(ids);
        return Result.success();
    }



}
