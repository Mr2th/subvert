package com.subvert.server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/3/28
 * @description
 */

@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "登录信息不能为空")
    private String loginInfo;
}
