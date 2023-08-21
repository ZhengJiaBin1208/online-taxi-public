package com.zjb.apipassenger.request;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

/**
 * @ClassName VerificationCodeDTO
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/11 18:01
 * @Version 1.0
 **/
@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String verificationCode;

}
