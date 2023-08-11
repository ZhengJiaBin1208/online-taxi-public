package com.zjb.apipassenger.controller;

import com.zjb.apipassenger.request.VerificationCodeDTO;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("verification-code")
    public String verificationCode(@ResponseBody VerificationCodeDTO verificationCodeDTO){
        return "";
    }
}
