package com.zjb.apipassenger.remote;

import com.zjb.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName ServiceVefificationcodeClient
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/18 15:16
 * @Version 1.0
 **/
@FeignClient("service-verificationcode")
public interface ServiceVefificationcodeClient {

    @GetMapping("/numberCode/{size}")
    ResponseResult getNumberCode(@PathVariable("size") int size);
}
