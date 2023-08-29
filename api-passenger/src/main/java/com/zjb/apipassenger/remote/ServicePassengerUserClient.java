package com.zjb.apipassenger.remote;

import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName ServicePassengerUserClient
 * @Description service-passenger-user服务feign调用
 * @Author zhengjiabin
 * @Date 2023/8/29 10:41
 * @Version 1.0
 **/
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @PostMapping("user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
