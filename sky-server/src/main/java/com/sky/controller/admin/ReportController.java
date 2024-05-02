package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report")
@Api(tags = "数据统计")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 营业额统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 指定时间的营业额
     */
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("统计营业额，时间段{} - {}, ", begin, end);
        return Result.success(reportService.getTurnoverStatistics(begin, end));
    }

    /**
     * 用户数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 一段时间内每日的总用户数和新增用户数
     */
    @GetMapping("/userStatistics")
    @ApiOperation("获取用户数据")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("用户数据统计，时间段{} - {}, ", begin, end);
        return Result.success(reportService.getUserStatistics(begin, end));
    }

    /**
     * 订单数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 指定时间内的所有订单和有效订单
     */
    @GetMapping("/ordersStatistics")
    @ApiOperation("订单数据统计")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("订单数据统计，时间段{} - {}", begin, end);
        return Result.success(reportService.getOrdersStatistics(begin, end));
    }

    /**
     * 销售数据统计
     * @param begin 查询的开始日期
     * @param end 查询的结束日期
     * @return 指定时间的销售数据
     */
    @GetMapping("/top10")
    @ApiOperation("销售数据统计")
    public Result<SalesTop10ReportVO> salesStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("销售数据统计，时间段{} - {}", begin, end);
        return Result.success(reportService.getSalesStatistics(begin, end));
    }

}
