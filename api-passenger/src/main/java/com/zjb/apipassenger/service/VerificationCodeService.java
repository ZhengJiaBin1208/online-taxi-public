package com.zjb.apipassenger.service;

import com.zjb.internalcommon.dto.ResponseResult;

/**
 * @ClassName VerificationCodeService
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/14 11:13
 * @Version 1.0
 **/
public interface VerificationCodeService {

    /**
     * @author zhengjiabin
     * @description 获取验证码
     * @date 2023/8/14 11:14
     * @param passengerPhone 手机号
     * @return java.lang.String
     **/
    ResponseResult generatorCode(String passengerPhone);

    /**
     * @author zhengjiabin
     * @description 验证验证码
     * @date 2023/8/21 14:12
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    ResponseResult checkCode(String passengerPhone,String verificationCode);
}
