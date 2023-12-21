package com.zjb.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @ClassName CommonStatusEnum
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/15 10:39
 * @Version 1.0
 **/
@Getter
public enum CommonStatusEnum {

    /**
     * 验证码错误提示: 1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    /**
     * Token类提示: 1100-1199
     */
    TOKEN_ERROR(1199,"token错误"),

    /**
     * 成功
     */
    SUCCESS(1,"success"),

    /**
     * 失败
     */
    FAIL(0,"fail")
    ;

    private int code;
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
