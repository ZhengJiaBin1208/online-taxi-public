package com.zjb.apipassenger.service.impl;

import com.zjb.apipassenger.service.VerificationCodeService;
import net.sf.json.JSONObject;
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
    /**
     * @param passengerPhone 手机号
     * @return java.lang.String
     * @author zhengjiabin
     * @description 获取验证码
     * @date 2023/8/14 11:14
     **/
    @Override
    public String generatorCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");

        // 存入redis
        System.out.println("存入redis");

        // 返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
