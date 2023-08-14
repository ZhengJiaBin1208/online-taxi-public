package com.zjb.apipassenger.controller;

import com.zjb.apipassenger.request.VerificationCodeDTO;
import com.zjb.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengePhone = verificationCodeDTO.getPassengePhone();
        System.out.println("接收到的手机号参数: "+passengePhone);
        return verificationCodeService.generatorCode(passengePhone);
    }
}
