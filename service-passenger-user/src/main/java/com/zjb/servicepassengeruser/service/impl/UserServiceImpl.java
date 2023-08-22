package com.zjb.servicepassengeruser.service.impl;

import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.request.VerificationCodeDTO;
import com.zjb.servicepassengeruser.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description 用户信息ServiceImpl
 * @Author zhengjiabin
 * @Date 2023/8/22 10:54
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    /**
     * @author zhengjiabin
     * @description 用户注册或登录接口
     * @date 2023/8/22 10:53
     * @param verificationCodeDTO
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("user service被调用,手机号: "+ passengerPhone);

        // 根据手机号查询用户信息

        // 判断用户信息是否存在

        // 如果不存在则插入用户信息
        return ResponseResult.success();
    }
}
