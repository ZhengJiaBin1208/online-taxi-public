package com.zjb.internalcommon;

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
