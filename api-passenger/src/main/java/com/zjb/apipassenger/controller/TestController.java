package com.zjb.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/11 17:49
 * @Version 1.0
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }
}
