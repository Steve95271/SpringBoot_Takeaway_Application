package com.sky.service;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {

    /**
     * 提交订单
     * @param dto 下单的信息
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO dto);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 根据订单ID查询订单详细信息
     * @param id 订单ID
     * @return OrderVO
     */
    OrderVO getById(Long id);

    /**
     * 查询历史订单
     * @param page 页面
     * @param pageSize 每页记录数
     * @param status 订单状态
     * @return 分页查询的结果
     */
    PageResult pageQuery(Integer page, Integer pageSize, Integer status);
}
