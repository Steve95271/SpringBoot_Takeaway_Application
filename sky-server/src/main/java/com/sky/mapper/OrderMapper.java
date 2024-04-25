package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {

    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 根据订单id查询信息
     * @param id 订单id
     * @return 订单信息
     */
    @Select("SELECT id, number, status, user_id, address_book_id, order_time, checkout_time, pay_method, " +
            "pay_status, amount, remark, phone, address, user_name, consignee, cancel_reason, rejection_reason, " +
            "cancel_time, estimated_delivery_time, delivery_status, delivery_time, pack_amount, tableware_number, tableware_status " +
            "FROM orders WHERE id = #{id}")
    Orders getById(Long id);

    /**
     * 分页查询历史订单
     * @param page
     * @param pageSize
     * @param status
     * @param userId
     * @return
     */
    Page<Orders> pageQuery(Integer page, Integer pageSize, Integer status, Long userId);

    /**
     * 订单搜索
     * @param dto 搜索条件
     * @return 分页查询结果
     */
    Page<Orders> pageQueryWithCondition(OrdersPageQueryDTO dto);

    /**
     * 根据状态统计订单数量
     * @param status
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);
}
