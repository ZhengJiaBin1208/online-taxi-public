package com.zjb.internalcommon.dto;

import com.zjb.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author zhengjiabin
 * @Date 2023/8/15 10:42
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * @author zhengjiabin
     * @description 成功响应方法
     * @date 2023/8/18 17:21
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    public static <T>ResponseResult success() {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

    /**
     * @author zhengjiabin
     * @description 默认成功的响应方法
     * @date 2023/8/16 10:34
     * @param data 返回的data对象
     * @param <T>
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }


    /**
     * @author zhengjiabin
     * @description 默认错误响应
     * @date 2023/8/16 10:40
     * @param data 返回的data对象
     * @param <T>
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setCode(CommonStatusEnum.FAIL.getCode()).setMessage(CommonStatusEnum.FAIL.getValue()).setData(data);
    }


    /**
     * @author zhengjiabin
     * @description 错误编码、错误信息错误响应
     * @date 2023/8/16 10:42
     * @param code 错误编码
     * @param message 错误信息
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * @author zhengjiabin
     * @description 错误编码、错误信息、data对象的错误响应
     * @date 2023/8/16 10:42
     * @param code 错误编码
     * @param message 错误信息
     * @param data 返回的data对象
     * @return com.zjb.internalcommon.dto.ResponseResult
     **/
    public static <T>ResponseResult fail(int code, String message, T data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
