package com.subvert.server.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
public class GetUserVo implements Serializable {

    private Long userId;

    private String username;

    private String phoneNumber;

    private String email;

    private String screenName;

    private String password;
}
