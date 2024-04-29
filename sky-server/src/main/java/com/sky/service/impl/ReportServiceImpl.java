package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.entity.User;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.ReportMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取一段时间内的营业额
     * @param begin 开始时间
     * @param end 结束时间
     * @return 一段时间内的营业额数据
     */
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();

        //把开始到结束的每一日放到集合当中
        //先加把begin的那天加进去。
        dateList.add(begin);
        //使用while循环把日期加一天之后添加到集合中，直到end的那天
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //存放每日营业额
        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            //设置当日的开始时间和结束时间 - 需要把LocalDate类型的变量转成LocalDateTime
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", Orders.COMPLETED);
            Double turnoverOfADay = orderMapper.sunByMap(map);

            //检查是否为空
            turnoverOfADay = turnoverOfADay == null ? 0.0 : turnoverOfADay;

            //添加到集合
            turnoverList.add(turnoverOfADay);
        }

        return TurnoverReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    /**
     * 用户数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 一段时间内每日的总用户数和新增用户数
     */
    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        //把每天的日期加到集合中
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Integer> totalUser = new ArrayList<>();
        List<Integer> newUser = new ArrayList<>();
        for (LocalDate date : dateList) {
            //设置当日的开始时间和结束时间 - 需要把LocalDate类型的变量转成LocalDateTime
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            //先放endTime进去用于查询到某一日为止的总用户数
            map.put("end", endTime);

            //查询某一日的总用户数量
            Integer total = userMapper.getUserCount(map);
            //判断是否为空
            total = total == null ? 0 : total;
            //Add to totalUser List
            totalUser.add(total);

            //把开始日期放进去map集合，当beginTime和endTime两个变量都有值的时候就能够查询到当日新增的用户
            map.put("begin", beginTime);
            Integer newUserInCurrentDay = userMapper.getUserCount(map);
            //Check if Null
            newUserInCurrentDay = newUserInCurrentDay == null ? 0 : newUserInCurrentDay;
            //Add to newUser List
            newUser.add(newUserInCurrentDay);
        }

        return UserReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .totalUserList(StringUtils.join(totalUser, ","))
                .newUserList(StringUtils.join(newUser, ","))
                .build();
    }

    /**
     * 订单数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 指定时间内的所有订单和有效订单
     */
    @Override
    public OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        //把每天的日期加到集合中
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Integer> ordersList = new ArrayList<>();
        List<Integer> completedOrdersList = new ArrayList<>();
        for (LocalDate date : dateList) {
            LocalDateTime beginTime= LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Integer ordersOfADay = getOrderCount(beginTime, endTime, null);
            ordersList.add(ordersOfADay);

            Integer completedOrdersOfADay = getOrderCount(beginTime, endTime, 5);
            completedOrdersList.add(completedOrdersOfADay);
        }

        Integer totalOrders = ordersList.stream().reduce(Integer::sum).get();
        Integer totalCompletedOrders = completedOrdersList.stream().reduce(Integer::sum).get();

        Double orderCompletedRate = 0.0;
        if (totalOrders != 0) {
            orderCompletedRate = totalCompletedOrders.doubleValue() / totalOrders;
        }

        return OrderReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .orderCountList(StringUtils.join(ordersList, ","))
                .validOrderCountList(StringUtils.join(completedOrdersList, ","))
                .totalOrderCount(totalOrders)
                .validOrderCount(totalCompletedOrders)
                .orderCompletionRate(orderCompletedRate)
                .build();

    }

    private Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer status) {
        Map map = new HashMap();
        map.put("begin", beginTime);
        map.put("end", endTime);
        map.put("status", status);

        return orderMapper.countByMap(map);
    }
}
