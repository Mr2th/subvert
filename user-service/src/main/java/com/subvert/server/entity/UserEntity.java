package com.subvert.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
@TableName("subvert_user")
public class UserEntity {

    @TableField("user_id")
    private Long userId;

    @TableField("username")
    private String username;

    @TableField("screen_name")
    private String screenName;

    @TableField("password")
    private String password;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("email")
    private String email;

    @TableField("create_time")
    private Date createTime;

    @TableField("modify_time")
    private Date modifyTime;
}
