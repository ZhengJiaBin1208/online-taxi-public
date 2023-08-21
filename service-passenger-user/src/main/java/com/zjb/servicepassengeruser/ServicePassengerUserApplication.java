package com.zjb.servicepassengeruser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ApiPassengerApplication
 * @Description 启动类
 * @Author zhengjiabin
 * @Date 2023/8/11 17:43
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ServicePassengerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}
