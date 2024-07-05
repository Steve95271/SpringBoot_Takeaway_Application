package com.onlineOrder.task;

import com.onlineOrder.entity.Orders;
import com.onlineOrder.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务class，用于定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超过15分钟后未支付的订单
     */
    @Scheduled(cron = "0 * * * * ? ") //每分钟触发一次
    //@Scheduled(cron = "1/5 * * * * ?") // 测试用
    public void processTimoutOrder() {
        log.info("定时处理超时订单: {}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> timeOutOrders = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if (timeOutOrders != null && timeOutOrders.size() > 0) {
            for (Orders timeOutOrder : timeOutOrders) {
                log.info("超时的订单: {}", timeOutOrder);
                timeOutOrder.setStatus(Orders.CANCELLED);
                timeOutOrder.setCancelReason("订单超时，自动取消");
                timeOutOrder.setCancelTime(LocalDateTime.now());
                orderMapper.update(timeOutOrder);
            }
        }
    }

    /**
     * 处理一直派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨一点触发
    //@Scheduled(cron = "0/5 * * * * ?") //测试用
    public void processDeliveringOrder() {
        log.info("处理一直派送中的订单: {}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().minusMinutes(60);

        List<Orders> deliveringOrders = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);

        if (deliveringOrders != null && deliveringOrders.size() > 0) {
            for (Orders timeOutOrder : deliveringOrders) {
                timeOutOrder.setStatus(Orders.COMPLETED);
                orderMapper.update(timeOutOrder);
            }
        }
    }



}
