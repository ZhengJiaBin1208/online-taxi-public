package com.zjb.apipassenger.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.zjb.apipassenger.remote.ServicePassengerUserClient;
import com.zjb.apipassenger.remote.ServiceVefificationcodeClient;
import com.zjb.apipassenger.service.VerificationCodeService;
import com.zjb.internalcommon.constant.CommonStatusEnum;
import com.zjb.internalcommon.constant.IdentityConstant;
import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.internalcommon.request.VerificationCodeDTO;
import com.zjb.internalcommon.response.NumberCodeResponse;
import com.zjb.internalcommon.response.TokenResponse;
import com.zjb.internalcommon.util.JwtUtils;
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

    // 乘客验证码的前缀
    private String verificationCodePrefix = "passenger-verification-code-";

    // token的前缀
    private String tokenPrefix = "token-";

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
        String key = generatorKeyByPhone(passengerPhone);
        // 存入redis
        stringRedisTemplate.opsForValue().set(key, String.valueOf(numberCode), 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上，阿里云短信服务、腾讯短信服务、华信、容联

        // 返回值
        return ResponseResult.success("");
    }

    /**
     * @author zhengjiabin
     * @description 获取验证码redis key
     * @date 2023/8/21 16:44
     * @param passengerPhone 手机号码
     * @return java.lang.String
     **/

    private String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

   /**
    * @author zhengjiabin
    * @description 生成token Rediskey
    * @date 2023/12/20 14:20 
    * @param phone 手机号
    * @param identity 身份标识
    * @return java.lang.String
    **/
    private String generatorTokenKey(String phone , String identity){
        return tokenPrefix + phone + "-" + identity;
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
        String redisKey = generatorKeyByPhone(passengerPhone);
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
        String token = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        // 将token存到redis当中
        String tokenKey = generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(tokenKey , token , 30 , TimeUnit.DAYS);
        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}
