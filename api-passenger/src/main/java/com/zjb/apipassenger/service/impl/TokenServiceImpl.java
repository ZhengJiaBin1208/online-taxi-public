package com.zjb.apipassenger.service.impl;

import com.zjb.apipassenger.service.TokenService;
import com.zjb.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @ClassName TokenServiceImpl
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/12/21 14:35
 * @Version 1.0
 **/
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * @param refreshToken
     * @return com.zjb.internalcommon.dto.ResponseResult
     * @author zhengjiabin
     * @description 校验刷新token并返回更新后的accessToken和refreshToken
     * @date 2023/12/21 14:33
     **/
    @Override
    public ResponseResult refreshToken(String refreshToken) {
        return null;
    }
}
