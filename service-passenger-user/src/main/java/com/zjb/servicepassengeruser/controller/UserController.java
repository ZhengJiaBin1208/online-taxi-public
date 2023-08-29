package com.zjb.servicepassengeruser.controller;

import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.internalcommon.request.VerificationCodeDTO;
import com.zjb.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description 乘客用户服务Controller
 * @Author zhengjiabin
 * @Date 2023/8/21 18:03
 * @Version 1.0
 **/
@RestController
public class UserController {

    @Autowired
     private UserService userService;

    /**
     * @author zhengjiabin
     * @description 用户登录或注册
     * @date 2023/8/21 18:09
     * @param verificationCodeDTO 用户信息DTO
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    @PostMapping("user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("passengerPhone: "+passengerPhone);
        ResponseResult responseResult = userService.loginOrRegister(passengerPhone);
        return responseResult;

    }
}
