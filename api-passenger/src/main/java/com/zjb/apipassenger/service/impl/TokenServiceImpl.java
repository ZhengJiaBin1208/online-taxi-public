package com.zjb.apipassenger.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.zjb.apipassenger.service.TokenService;
import com.zjb.internalcommon.constant.CommonStatusEnum;
import com.zjb.internalcommon.constant.IdentityConstant;
import com.zjb.internalcommon.constant.TokenTypeConstant;
import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.internalcommon.dto.TokenResult;
import com.zjb.internalcommon.response.TokenResponse;
import com.zjb.internalcommon.util.JwtUtils;
import com.zjb.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenServiceImpl
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/12/21 14:35
 * @Version 1.0
 **/
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * @param refreshToken
     * @return com.zjb.internalcommon.dto.ResponseResult
     * @author zhengjiabin
     * @description 校验刷新token并返回更新后的accessToken和refreshToken
     * @date 2023/12/21 14:33
     **/
    @Override
    public ResponseResult refreshToken(String refreshToken) {

        // 解析refreshToken
        TokenResult tokenResult = JwtUtils.parseToken(refreshToken);
        if (null == tokenResult) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        // 读取redis中的refreshToken
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        String tokenType = tokenResult.getTokenType();

        String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, tokenType);
        String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);

        // 校验refreshToken
        if (StringUtils.isBlank(redisToken) || !redisToken.trim().equals(refreshToken.trim())) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        // 生成token
        String accessToken = JwtUtils.generatorToken(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.ACCESS_TOKEN);
        String newRefreshToken = JwtUtils.generatorToken(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.REFRESH_TOKEN);

        // 将token存到redis当中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.ACCESS_TOKEN);

//        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30 , TimeUnit.DAYS);
//        stringRedisTemplate.opsForValue().set(tokenKey , refreshToken , 31 , TimeUnit.DAYS);

        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 10 , TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(tokenKey , newRefreshToken , 30 , TimeUnit.SECONDS);

        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(newRefreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
