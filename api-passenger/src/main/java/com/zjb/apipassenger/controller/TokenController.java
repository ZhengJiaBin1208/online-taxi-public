package com.zjb.apipassenger.controller;

import com.zjb.apipassenger.service.TokenService;
import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TokenController
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/12/21 14:28
 * @Version 1.0
 **/
@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("refreshToken: " + refreshToken);

        return tokenService.refreshToken(refreshToken);
    }
}
