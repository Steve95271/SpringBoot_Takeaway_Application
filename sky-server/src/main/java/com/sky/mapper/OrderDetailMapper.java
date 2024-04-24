package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * 插入n条订单明细数据
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);

    /**
     * 根据订单id查询明细信息
     * @param id 订单id
     * @return 订单明细
     */
    @Select("SELECT * FROM order_detail where order_id = #{id}")
    List<OrderDetail> getById(Long id);
}
