package com.zjb.internalcommon.util;

/**
 * @ClassName RedisPrefixUtils
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/12/20 14:58
 * @Version 1.0
 **/
public class RedisPrefixUtils {

    // 乘客验证码的前缀
    private static String verificationCodePrefix = "passenger-verification-code-";

    // token的前缀
    private static String tokenPrefix = "token-";

    /**
     * @author zhengjiabin
     * @description 获取验证码redis key
     * @date 2023/8/21 16:44
     * @param passengerPhone 手机号码
     * @return java.lang.String
     **/

    public static String generatorKeyByPhone(String passengerPhone) {
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
    public static String generatorTokenKey(String phone, String identity){
        return tokenPrefix + phone + "-" + identity;
    }
}
