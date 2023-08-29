package com.zjb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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

    // JWT_KEY
    private static final String JWT_KEY="passengerPhone";


    // 生成token
    public static String generatorToken(String passengerPhone){
        // 整合map
        HashMap<String, String> map = new HashMap<>();
        map.put(JWT_KEY,passengerPhone);

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
        builder.withExpiresAt(date);

        // 生成token
        String sign = builder.sign(Algorithm.HMAC512(SIGN));
        return sign;
    }

    // 解析token
    public static String parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC512(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.asString();
    }
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        String token = generatorToken("18513516300");
        System.out.println(token);
        System.out.println(parseToken(token));
    }
}
