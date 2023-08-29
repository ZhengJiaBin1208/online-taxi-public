package com.zjb.internalcommon.dto;

import lombok.Data;

/**
 * @ClassName TokenResult
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/29 14:43
 * @Version 1.0
 **/
@Data
public class TokenResult {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 乘客端或司机端标识
     *  1:乘客端
     *  2:司机端
     */
    private String identity;
}
