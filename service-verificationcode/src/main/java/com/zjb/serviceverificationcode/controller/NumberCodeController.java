package com.zjb.serviceverificationcode.controller;

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
    public String numberCode(@PathVariable("size") int size){
        System.out.println("size: "+ size);

        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        int numberCode = (int) (Math.random() * Math.pow(10,size));
        System.out.println("numberCode:"+numberCode);
        data.put("numberCode",numberCode);
        result.put("code",1);
        result.put("message","success");
        result.put("data",data);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println((int)(Math.random() * 1000000));
    }
}
