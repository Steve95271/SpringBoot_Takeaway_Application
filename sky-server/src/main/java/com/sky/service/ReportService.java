package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

public interface ReportService {

    /**
     * 获取一段时间内的营业额
     * @param begin 开始时间
     * @param time 结束时间
     * @return 一段时间内的营业额数据
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate time);

}
