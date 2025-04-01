package com.subvert.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Data
public class QueryPageUserDto implements Serializable {

    private String searchWord;

    private Integer pageIndex = 1;

    private Integer pageSize = 20;
}
