package com.zjb.apipassenger.service;

import com.zjb.internalcommon.dto.ResponseResult;

/**
 * @ClassName TokenService
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/12/21 14:31
 * @Version 1.0
 **/
public interface TokenService {

    /**
     * @author zhengjiabin
     * @description 校验刷新token并返回更新后的accessToken和refreshToken
     * @date 2023/12/21 14:33
     * @param refreshToken
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    ResponseResult refreshToken(String refreshToken);
}
