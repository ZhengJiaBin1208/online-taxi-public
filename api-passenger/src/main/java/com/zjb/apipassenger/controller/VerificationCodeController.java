package com.zjb.apipassenger.controller;

import com.zjb.apipassenger.request.VerificationCodeDTO;
import com.zjb.apipassenger.service.VerificationCodeService;
import com.zjb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName VerificationCodeController
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/11 17:59
 * @Version 1.0
 **/
@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    /**
     * @author zhengjiabin
     * @description 生成验证码
     * @date 2023/8/21 14:09
     * @param passengePhone 手机号
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    @GetMapping("verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengePhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号参数: "+passengePhone);
        return verificationCodeService.generatorCode(passengePhone);
    }

   /**
    * @author zhengjiabin
    * @description TODO
    * @date 2023/8/21 14:10
    * @param passengePhone 手机号
    * @param verificationCode 验证码
    * @return com.zjb.internalcommon.dto.ResponseResult
    **/
    @PostMapping("verification-code-check")
    public ResponseResult verificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("passengePhone:"+passengerPhone);
        System.out.println("verificationCode:"+verificationCode);
        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }
}
