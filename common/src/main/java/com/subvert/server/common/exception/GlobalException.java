package com.subvert.server.common.exception;

import com.subvert.server.common.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {

    private final String code;

    private final String message;

    public GlobalException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDescription();
    }

    public GlobalException(String message) {
        this.code = ResultCode.UNKNOWN_ERROR_CODE.getCode();
        this.message = message;
    }

    public GlobalException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
