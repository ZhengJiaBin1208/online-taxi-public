package com.zjb.apipassenger.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjb.apipassenger.remote.ServiceVefificationcodeClient;
import com.zjb.apipassenger.service.VerificationCodeService;
import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.response.NumberCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        System.out.println("numberCodeResponse"+JSON.toJSONString(numberCodeResponse));

        // 存入redis
        System.out.println("存入redis");

        // 返回值
        return numberCodeResponse;
    }
}
