package com.zjb.servicepassengeruser.controller;

import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.request.VerificationCodeDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName UserController
 * @Description 乘客用户服务Controller
 * @Author zhengjiabin
 * @Date 2023/8/21 18:03
 * @Version 1.0
 **/
public class UserController {

    /**
     * @author zhengjiabin
     * @description 用户登录或注册
     * @date 2023/8/21 18:09
     * @param verificationCodeDTO 用户信息DTO
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    @PostMapping("/user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("passengerPhone: "+passengerPhone);
        return ResponseResult.success();
    }
}
