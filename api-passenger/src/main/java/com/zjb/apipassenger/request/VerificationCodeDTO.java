package com.zjb.apipassenger.request;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @ClassName VerificationCodeDTO
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/11 18:01
 * @Version 1.0
 **/
public class VerificationCodeDTO {

    private String passengePhone;

    public String getPassengePhone() {
        return passengePhone;
    }

    public void setPassengePhone(String passengePhone) {
        this.passengePhone = passengePhone;
    }
}
