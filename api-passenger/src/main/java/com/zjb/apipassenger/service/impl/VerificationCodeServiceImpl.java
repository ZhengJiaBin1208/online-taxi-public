package com.zjb.apipassenger.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.zjb.apipassenger.remote.ServicePassengerUserClient;
import com.zjb.apipassenger.remote.ServiceVefificationcodeClient;
import com.zjb.apipassenger.service.VerificationCodeService;
import com.zjb.internalcommon.constant.CommonStatusEnum;
import com.zjb.internalcommon.constant.IdentityConstant;
import com.zjb.internalcommon.constant.TokenTypeConstant;
import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.internalcommon.request.VerificationCodeDTO;
import com.zjb.internalcommon.response.NumberCodeResponse;
import com.zjb.internalcommon.response.TokenResponse;
import com.zjb.internalcommon.util.JwtUtils;
import com.zjb.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName VerificationCodeServiceImpl
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/14 11:15
 * @Version 1.0
 **/
@Service
public class VerificationCodeServiceImpl  implements VerificationCodeService {



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceVefificationcodeClient serviceVefificationcodeClient;

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    /**
     * @param passengerPhone 手机号
     * @return java.lang.String
     * @author zhengjiabin
     * @description 获取验证码
     * @date 2023/8/14 11:14
     **/
    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        System.out.println("numberCodeResponse"+JSON.toJSONString(numberCodeResponse));
        int numberCode = numberCodeResponse.getData().getNumberCode();
        // key,value,过期时间
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        // 存入redis
        stringRedisTemplate.opsForValue().set(key, String.valueOf(numberCode), 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上，阿里云短信服务、腾讯短信服务、华信、容联

        // 返回值
        return ResponseResult.success("");
    }



    /**
     * @description 验证验证码
     * @author zhengjiabin
     * @date 2023/8/21 14:12
     * @param passengerPhone   手机号
     * @param verificationCode 验证码
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    @Override
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        // 1.根据手机号从redis中获取验证码
        // 拼接redis key
        String redisKey = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        System.out.println("拼接的redisKey: "+redisKey);
        String redisVerificationCode = stringRedisTemplate.opsForValue().get(redisKey);
        System.out.println("获取的redisVerificationCode: "+redisVerificationCode);

        // 2.校验验证码是否相同
        if (StringUtils.isNotBlank(redisVerificationCode) &&redisVerificationCode.equals(verificationCode)) {
            System.out.println("验证码相同");
        }else {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 3.如果原来没有用户插入，有用户则查询
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

//        generatorCode(passengerPhone);
        // 4.生成token
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.ACCESS_TOKEN);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.REFRESH_TOKEN);

        // 将token存到redis当中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.ACCESS_TOKEN);
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenTypeConstant.REFRESH_TOKEN);

//        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30 , TimeUnit.DAYS);
//        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 31 , TimeUnit.DAYS);

        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 10 , TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 30 , TimeUnit.SECONDS);

        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
