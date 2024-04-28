package com.sky.service;

import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

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
}
