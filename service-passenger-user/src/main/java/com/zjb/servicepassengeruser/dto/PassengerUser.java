package com.zjb.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName PassengerUser
 * @Description TODO
 * @Author Administrator
 * @Date 2023/8/23 0:25
 * @Version 1.0
 **/
@Data
public class PassengerUser {

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;

}
