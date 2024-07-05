package com.onlineOrder.mapper;

import com.onlineOrder.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User getByOpenId(String openid);


    /**
     * 插入新用户
     * @param user
     */
    void insert(User user);

    /**
     * 根据用户ID获取用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(Long id);

    /**
     * Get user statistics
     * @param map
     * @return
     */
    Integer getUserCount(Map map);
}
