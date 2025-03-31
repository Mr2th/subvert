package com.subvert.server.common.exception;

import lombok.Data;

/**
 * @author xujianguo
 * @date 2025/3/27
 * @description
 */

@Data
public class ErrorResponse {

    private String code;
    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
