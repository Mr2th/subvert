package com.subvert.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
public class GetUserDto implements Serializable {

    private Long userId;

    private String username;

    private String password;

    public GetUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
