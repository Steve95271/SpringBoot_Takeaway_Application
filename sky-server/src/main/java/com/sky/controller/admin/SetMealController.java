package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关Controller")
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    @PostMapping
    @ApiOperation("新增套餐")
    public Result addSetMeal(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐: {}", setmealDTO);
        setMealService.addSetMeal(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result<PageResult> pagingQuerySetMeals(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询套餐列表: {}", setmealPageQueryDTO);
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据id查询套餐: {}", id);
        return Result.success(setMealService.getById(id));
    }

    @DeleteMapping
    @ApiOperation("根据ID删除套餐")
    public Result delete(@RequestParam("ids") List<Long> ids) {
        log.info("根据id删除套餐: {}", ids);
        setMealService.delete(ids);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("更新套餐信息")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("更新套餐: {}", setmealDTO);
        setMealService.update(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售、停售")
    public Result changeStatus(@PathVariable Integer status, @RequestParam("id") Long id) {
        log.info("套餐起售、停售，状态参数: {} ID参数: {}", status, id);
        setMealService.changeStatus(status, id);
        return Result.success();
    }

}
