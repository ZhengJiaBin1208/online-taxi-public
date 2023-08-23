package com.zjb.servicepassengeruser.service;

import com.zjb.internalcommon.dto.ResponseResult;


/**
 * @ClassName UserService
 * @Description 用户信息Service
 * @Author zhengjiabin
 * @Date 2023/8/22 10:52
 * @Version 1.0
 **/
public interface UserService {


    /**
     * @author zhengjiabin
     * @description 用户注册或登录接口
     * @date 2023/8/22 10:53
     * @param verificationCodeDTO
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    ResponseResult loginOrRegister(String passengerPhone);
}
