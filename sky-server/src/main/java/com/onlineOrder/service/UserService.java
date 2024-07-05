package com.onlineOrder.service;

import com.onlineOrder.dto.UserLoginDTO;
import com.onlineOrder.entity.User;

public interface UserService {

    /**
     * 微信登陆
     * @param userLoginDTO 前端传过来的登陆信息
     * @return 用户信息
     */
    User wxLogin(UserLoginDTO userLoginDTO);

}
