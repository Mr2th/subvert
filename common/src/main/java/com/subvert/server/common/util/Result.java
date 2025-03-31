package com.subvert.server.common.util;

import com.subvert.server.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description
 */

@Data
public class Result<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS_CODE.getCode());
        result.setMessage(ResultCode.SUCCESS_CODE.getDescription());
        return result;
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getDescription());
        return result;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.UNKNOWN_ERROR_CODE.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.setData(data);
        return result;
    }
}
