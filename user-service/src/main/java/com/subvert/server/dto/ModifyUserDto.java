package com.subvert.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
public class ModifyUserDto implements Serializable {

    private Long userId;

    private String password;

    private String phoneNumber;

    private String email;

    private String screenName;
}
