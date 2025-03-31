package com.subvert.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/3/26
 * @description
 */

@Data
public class LoginVo implements Serializable {

    private String token;

    public LoginVo(String token) {
        this.token = token;
    }
}
