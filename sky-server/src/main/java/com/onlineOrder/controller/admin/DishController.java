package com.onlineOrder.controller.admin;

import com.onlineOrder.dto.DishDTO;
import com.onlineOrder.dto.DishPageQueryDTO;
import com.onlineOrder.entity.Dish;
import com.onlineOrder.result.PageResult;
import com.onlineOrder.result.Result;
import com.onlineOrder.service.DishService;
import com.onlineOrder.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

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

        //添加新菜品后清理redis的缓存
        String key = "dish_" + dishDTO.getCategoryId();
        cleanRedisCache(key);

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

        //清除所有缓存
        cleanRedisCache("dish_*");

        return Result.success();
    }

    /**
     * 根据id获得菜品
     * @param id 菜品ID
     * @return DishVO对象
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询菜品")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("根据ID查询菜品，id: {}", id);
        DishVO dishVO = dishService.getDishWithFlavorById(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品信息
     * @param dishDTO 菜品数据传输对象
     * @return code : 0
     */
    @PutMapping
    @ApiOperation("修改菜品信息")
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品信息: {}", dishDTO.toString());
        dishService.updateDish(dishDTO);

        //清除所有缓存
        cleanRedisCache("dish_*");

        return Result.success();
    }

    /**
     * 根据分类ID获取菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类ID获取菜品")
    public Result<List<Dish>> getDishByCategoryId(@RequestParam Long categoryId) {
        log.info("根据分类ID获取菜品: {}", categoryId);
        List<Dish> dishList = dishService.getDishByCategoryId(categoryId);
        return Result.success(dishList);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售/停售")
    public Result<String> changeStatus(@PathVariable Integer status, Long id) {
        log.info("菜品起售/停售 -- status: {} ID: {}", status, id);
        dishService.changeStatus(status, id);

        //清除所有缓存
        cleanRedisCache("dish_*");

        return Result.success();
    }

    private void cleanRedisCache(String patten) {
        Set keys = redisTemplate.keys(patten);
        redisTemplate.delete(keys);
    }
}
