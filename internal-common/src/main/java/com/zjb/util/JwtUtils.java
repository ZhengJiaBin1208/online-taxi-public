package com.zjb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

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

    // 生成token
    public static String generatorToken(Map<String,String> map){
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
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    // 解析token
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","zjb");
        map.put("age","18");
        System.out.println(generatorToken(map));
    }
}
