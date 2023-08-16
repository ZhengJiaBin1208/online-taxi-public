package com.zjb.serviceverificationcode.controller;

import com.zjb.internalcommon.dto.ResponseResult;
import com.zjb.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName NumberCodeController
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/14 17:42
 * @Version 1.0
 **/
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size: "+ size);
        int numberCode = (int) ((Math.random() * 9 + 1) * Math.pow(10,size-1));
        System.out.println("numberCode:"+numberCode);

        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(numberCode);
        return ResponseResult.success(numberCodeResponse);
    }

}
