package com.subvert.server.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
public class RemoveUserDto implements Serializable {

    private Long userId;

    private List<Long> userIds;
}
