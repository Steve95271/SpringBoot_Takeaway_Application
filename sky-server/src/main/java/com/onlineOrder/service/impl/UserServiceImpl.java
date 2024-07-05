package com.onlineOrder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.onlineOrder.constant.MessageConstant;
import com.onlineOrder.dto.UserLoginDTO;
import com.onlineOrder.entity.User;
import com.onlineOrder.exception.LoginFailedException;
import com.onlineOrder.mapper.UserMapper;
import com.onlineOrder.properties.WeChatProperties;
import com.onlineOrder.service.UserService;
import com.onlineOrder.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        //调用微信服务接口
        String openid = getOpenId(userLoginDTO.getCode());

        //判断openid是否为空，如果为空表示登陆失败，抛出业务异常
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //判断当前用户是否为新用户
        User user = userMapper.getByOpenId(openid);

        //如果用户为新用户，则要为此用户创建
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();

            userMapper.insert(user);
        }

        return user;
    }

    private String getOpenId(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String wxLoginResponseData = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(wxLoginResponseData);
        return jsonObject.getString("openid");
    }
}
