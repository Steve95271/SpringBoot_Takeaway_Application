package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public interface ReportService {

    /**
     * 获取一段时间内的营业额
     * @param begin 开始时间
     * @param time 结束时间
     * @return 一段时间内的营业额数据
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate time);

    /**
     * 用户数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 一段时间内每日的总用户数和新增用户数
     */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    /**
     * 订单数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 指定时间内的所有订单和有效订单
     */
    OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end);

    /**
     * 销售数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 指定时间的销售数据
     */
    SalesTop10ReportVO getSalesStatistics(LocalDate begin, LocalDate end);

    /**
     * 导出运营数据报表
     * @param response
     */
    void exportBusinessData(HttpServletResponse response);
}
