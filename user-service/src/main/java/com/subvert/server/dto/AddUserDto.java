package com.subvert.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
public class AddUserDto implements Serializable {

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String screenName;
}
