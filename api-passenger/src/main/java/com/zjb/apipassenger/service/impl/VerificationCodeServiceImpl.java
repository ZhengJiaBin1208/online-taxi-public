package com.zjb.apipassenger.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjb.apipassenger.remote.ServiceVefificationcodeClient;
import com.zjb.apipassenger.service.VerificationCodeService;
import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.response.NumberCodeResponse;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceVefificationcodeClient serviceVefificationcodeClient;
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
        String key = verificationCodePrefix + passengerPhone;
        // 存入redis
        stringRedisTemplate.opsForValue().set(key, String.valueOf(numberCode), 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上，阿里云短信服务、腾讯短信服务、华信、容联

        // 返回值
        return ResponseResult.success("");
    }
}
