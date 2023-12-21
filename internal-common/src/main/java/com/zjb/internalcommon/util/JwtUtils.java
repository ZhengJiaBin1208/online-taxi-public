package com.zjb.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zjb.internalcommon.constant.TokenTypeConstant;
import com.zjb.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtUtils
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/29 11:31
 * @Version 1.0
 **/
public class JwtUtils {

    // 盐值
    private static final String SIGN="ASDzjb~!@#$$";

    // JWT_KEY_PHONE
    private static final String JWT_KEY_PHONE ="phone";

    // 乘客端是1，司机端是2
    private static final String JWT_KEY_IDENTITY ="identity";


    private static final String JWT_TOKEN_TYPE ="tokenType";



    // 生成token
    public static String generatorToken(String phone, String identity, String tokenType){
        // 整合map
        HashMap<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,phone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);


        // token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        // 整合map
        map.forEach(
                (k,v) -> {
                    System.out.println("k: "+ k + ", v: " + v);
                    builder.withClaim(k,v);
                }
        );

        // 整合过期时间
//        builder.withExpiresAt(date);

        // 生成token
        String sign = builder.sign(Algorithm.HMAC512(SIGN));
        return sign;
    }

    // 解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC512(SIGN)).build().verify(token);
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(verify.getClaim(JWT_KEY_PHONE).asString());
        tokenResult.setIdentity(verify.getClaim(JWT_KEY_IDENTITY).asString());
        return tokenResult;
    }
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        String token = generatorToken("18513516300","1", TokenTypeConstant.ACCESS_TOKEN);
        System.out.println(token);
        System.out.println(parseToken(token));
    }
}
