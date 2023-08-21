package com.zjb.request;

import lombok.Data;

/**
 * @ClassName VerificationCodeDTO
 * @Description 用户信息DTO
 * @Author zhengjiabin
 * @Date 2023/8/11 18:01
 * @Version 1.0
 **/
@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String verificationCode;

}
