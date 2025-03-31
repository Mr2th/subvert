package com.subvert.server.common.util.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description
 */

@Data
public class AuthEntity implements Serializable {

    private String userName;

    private String passWord;
}
