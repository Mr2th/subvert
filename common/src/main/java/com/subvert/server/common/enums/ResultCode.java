package com.subvert.server.common.enums;

import lombok.Getter;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description
 */

@Getter
public enum ResultCode {

    SUCCESS_CODE("S600200", "成功"),

    UNKNOWN_ERROR_CODE("F600400", "系统异常"),

    VALIDA_ERROR_CODE("F600401", "参数校验失败"),

    GET_USER_ERROR_CODE("F600402", "用户名获取失败，请联系管理员"),

    AUTH_ERROR_CODE("F600403", "token校验失败");

    private final String code;

    private final String description;

    ResultCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
